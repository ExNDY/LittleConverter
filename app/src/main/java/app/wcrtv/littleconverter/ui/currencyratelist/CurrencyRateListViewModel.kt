package app.wcrtv.littleconverter.ui.currencyratelist

import androidx.lifecycle.*
import app.wcrtv.littleconverter.data.network.model.Valute
import app.wcrtv.littleconverter.repository.Repository
import kotlinx.coroutines.launch

class CurrencyRateListViewModel : ViewModel() {
    private var _rateList = MutableLiveData<ArrayList<Valute>?>()

    init {
        viewModelScope.launch {
            val repository = Repository()
/*
            _rateList.asFlow().collect{

            }*/
            repository.loadData()
/*
            repository.getLoadedData().observe(, {response ->
                if (response != null){
                    _rateList.postValue(response.valute.values as ArrayList<Valute>?)
                }
            })*/
        }
    }

    fun getRateList(): LiveData<ArrayList<Valute>?> {


        return _rateList
    }
}