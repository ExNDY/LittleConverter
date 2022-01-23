package app.wcrtv.littleconverter.ui.converter

import androidx.lifecycle.ViewModel
import app.wcrtv.littleconverter.model.CbrResponse

class ConverterViewModel : ViewModel() {
    //private var repository = Repository()
    private var data: CbrResponse? = null
    private var choosenCurrencyFrom = "RUB"
    private var choosenCurrencyTo = "USD"
    // TODO: Implement the ViewModel
    fun initData(){
        //val cbrResponse: CbrResponse? = repository.loadData()
        //var valute: Valute? = cbrResponse?.valute?.get("AUD")
        //var from = getValuteValue("RUB")
    }

    private fun getValuteValue(charCode: String): Double? {
        if (data != null){
            return data?.valute?.get(charCode)?.value
        }

        return 0.0
    }
}