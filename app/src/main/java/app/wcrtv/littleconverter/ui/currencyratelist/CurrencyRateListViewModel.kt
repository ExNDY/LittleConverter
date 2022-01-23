package app.wcrtv.littleconverter.ui.currencyratelist

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.wcrtv.littleconverter.model.CbrResponse
import app.wcrtv.littleconverter.model.Valute
import app.wcrtv.littleconverter.repository.Repository
import app.wcrtv.littleconverter.utils.ListUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CurrencyRateListViewModel(private val repository: Repository) : ViewModel() {
    private val _rateList = MutableLiveData<ArrayList<Valute>>()
    private val _data = MutableLiveData<CbrResponse?>()
    private val _errorMessage = MutableLiveData<String>()

    private var restoredData: LiveData<CbrResponse>? = null

    fun loadLatestData(context: Context){
        val response = repository.getLatestDailyRates()

        response.enqueue(object: Callback<CbrResponse?> {
            override fun onResponse(call: Call<CbrResponse?>, response: Response<CbrResponse?>) {
                _data.postValue(response.body())

                _data.value?.valute?.let { convertMapToList(it) }

                _data.value?.let { saveData(context, it) }
            }

            override fun onFailure(call: Call<CbrResponse?>, t: Throwable) {
                _errorMessage.postValue(t.message)
            }
        })
    }

    fun getValutesList():LiveData<ArrayList<Valute>>{
        return _rateList
    }

    fun getErrorMessage():LiveData<String>{
        return _errorMessage
    }

    fun saveData(context:Context, response: CbrResponse){
        Repository.insertData(context, response)
    }

    fun restoreData(context:Context) : LiveData<CbrResponse>? {
        restoredData = Repository.getLocalData(context)

        return restoredData
    }

    fun restoreFromData(data: CbrResponse){
        viewModelScope.launch(Dispatchers.IO) {
            _rateList.postValue(ListUtils.convertMapToList(data.valute))
        }
    }

    private fun convertMapToList(map: Map<String, Valute>){
        viewModelScope.launch(Dispatchers.IO) {
            _rateList.postValue(ListUtils.convertMapToList(map))
        }
    }
}