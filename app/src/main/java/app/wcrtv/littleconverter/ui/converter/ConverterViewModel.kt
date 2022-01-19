package app.wcrtv.littleconverter.ui.converter

import androidx.lifecycle.ViewModel
import app.wcrtv.littleconverter.model.DataJson
import app.wcrtv.littleconverter.model.Valute
import app.wcrtv.littleconverter.repository.Repository

class ConverterViewModel : ViewModel() {
    private var repository = Repository()
    private var data: DataJson? = null
    private var choosenCurrencyFrom = "RUB"
    private var choosenCurrencyTo = "USD"
    // TODO: Implement the ViewModel
    fun initData(){
        val dataJson: DataJson? = repository.loadData()
        var valute: Valute? = dataJson?.valute?.get("AUD")
        var from = getValuteValue("RUB")
    }

    private fun getValuteValue(charCode: String): Double? {
        if (data != null){
            return data?.valute?.get(charCode)?.value
        }

        return 0.0
    }
}