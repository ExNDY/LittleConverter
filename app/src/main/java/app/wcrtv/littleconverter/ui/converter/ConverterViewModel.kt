package app.wcrtv.littleconverter.ui.converter

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.wcrtv.littleconverter.model.CbrResponse
import app.wcrtv.littleconverter.model.Valute
import app.wcrtv.littleconverter.repository.Repository
import app.wcrtv.littleconverter.utils.CurrencyConverter
import app.wcrtv.littleconverter.utils.ListUtils
import app.wcrtv.littleconverter.utils.TextUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ConverterViewModel : ViewModel() {
    private val _errorMessage = MutableLiveData<String>()
    private val _fromFieldValue = MutableLiveData<String>()
    private val _toFieldValue = MutableLiveData<String>()
    private val _chosenCurrencyTo = MutableLiveData<Valute>()
    private var allValutesList = ArrayList<Valute>()
    private var restoredData: LiveData<CbrResponse>? = null

    init {
        _fromFieldValue.postValue("0")
        _toFieldValue.postValue("0")
    }

    fun getErrorMessage(): LiveData<String> {
        return _errorMessage
    }

    fun getToFieldValue(): LiveData<String> {
        return _toFieldValue
    }

    fun getFromFieldValue(): LiveData<String> {
        return _fromFieldValue
    }

    fun getChosenCurrency():LiveData<Valute>{
        return _chosenCurrencyTo
    }

    fun restoreData(context: Context): LiveData<CbrResponse>? {
        restoredData = Repository.getLocalData(context)

        return restoredData
    }

    fun restoreFromData(data: CbrResponse){
        viewModelScope.launch(Dispatchers.IO) {
            allValutesList = ListUtils.convertMapToList(data.valute)

            _chosenCurrencyTo.postValue(allValutesList[2])
        }
    }

    fun changeChosenValute(index: Int){
        _chosenCurrencyTo.postValue(allValutesList[index])
    }

    fun calc(count: String) {
        val countDouble: Double
        try {
            countDouble = TextUtils.parseStringToDouble(count)

            val value = CurrencyConverter.convert(_chosenCurrencyTo.value,countDouble)

            _toFieldValue.postValue(TextUtils.formattingDifferenceValue(value))
        } catch (e:NumberFormatException) {
            _errorMessage.postValue(e.toString())
        }
    }

    fun inputNum(value: Int){
        val fromValue = _fromFieldValue.value

        if (fromValue != null){
            val length = fromValue.length

            if (fromValue.contains(".")){
                val checkDot = fromValue.indexOf(".")
                val diff = length - checkDot

                if (diff <= 2) {
                    if (fromValue == "0"){
                        _fromFieldValue.postValue(value.toString())
                    } else {
                        _fromFieldValue.postValue("$fromValue" + value.toString())
                    }
                } else {
                    _errorMessage.postValue("Limit symbols after dot")
                }
            } else {
                if (fromValue == "0"){
                    _fromFieldValue.postValue(value.toString())
                } else {
                    _fromFieldValue.postValue("$fromValue" + value.toString())
                }
            }
        }
    }

    fun inputDot(){
        val fromValue = _fromFieldValue.value

        if (fromValue != null){
            val checkDot = fromValue.indexOf(".")

            if (checkDot == -1){
                _fromFieldValue.postValue("$fromValue.")
            } else {
                _errorMessage.postValue("Dot has in line")
            }
        }
    }

    fun inputDel(){
        val fromValue = _fromFieldValue.value

        if (fromValue != null){
            if (fromValue.isNotEmpty()){
                if (fromValue.length - 1 == 0){
                    _fromFieldValue.postValue("0")
                } else {
                    _fromFieldValue.postValue(fromValue.substring(0, fromValue.length - 1))
                }
            }
        }
    }

}