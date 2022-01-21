package app.wcrtv.littleconverter.ui.currencyratelist

import androidx.lifecycle.*
import app.wcrtv.littleconverter.data.network.model.CbrResponse
import app.wcrtv.littleconverter.data.network.model.Valute
import app.wcrtv.littleconverter.repository.Repository
import app.wcrtv.littleconverter.utils.ListUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CurrencyRateListViewModel(private val repository: Repository) : ViewModel() {
    private val _rateList = MutableLiveData<ArrayList<Valute>>()
    private val _response = MutableLiveData<CbrResponse?>()
    private val _errorMessage = MutableLiveData<String>()

    init {
        viewModelScope.launch {
            getLatestDailyRates()
        }
    }

    fun getLatestDailyRates(){
        val response = repository.getLatestDailyRates()

        response.enqueue(object: Callback<CbrResponse?> {
            override fun onResponse(call: Call<CbrResponse?>, response: Response<CbrResponse?>) {
                _response.postValue(response.body())

                _response.value?.valute?.let { convertMapToList(it) }
            }

            override fun onFailure(call: Call<CbrResponse?>, t: Throwable) {
                _errorMessage.postValue(t.message)
            }
        })
    }

    fun getDailyRates():LiveData<ArrayList<Valute>>{
        return _rateList
    }

    fun getErrorMessage():LiveData<String>{
        return _errorMessage
    }

    private fun convertMapToList(map: Map<String, Valute>){
        viewModelScope.launch(Dispatchers.IO) {
            _rateList.postValue(ListUtils.convertMapToList(map))
        }
    }
}