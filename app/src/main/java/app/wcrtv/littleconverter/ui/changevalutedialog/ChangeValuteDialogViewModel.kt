package app.wcrtv.littleconverter.ui.changevalutedialog

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

class ChangeValuteDialogViewModel:ViewModel() {
    private val _allValutesList = MutableLiveData<ArrayList<Valute>>()
    private var restoredData: LiveData<CbrResponse>? = null

    fun getAllValutesList() : LiveData<ArrayList<Valute>>{
        return _allValutesList
    }

    fun restoreData(context: Context) : LiveData<CbrResponse>? {
        restoredData = Repository.getLocalData(context)

        return restoredData
    }

    fun restoreFromData(data: CbrResponse){
        viewModelScope.launch(Dispatchers.IO) {
            _allValutesList.postValue(ListUtils.convertMapToList(data.valute))
        }
    }

    private fun convertMapToList(map: Map<String, Valute>){
        viewModelScope.launch(Dispatchers.IO) {
            _allValutesList.postValue(ListUtils.convertMapToList(map))
        }
    }
}