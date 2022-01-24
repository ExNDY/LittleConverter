package app.wcrtv.littleconverter.utils

import app.wcrtv.littleconverter.model.Valute

object CurrencyConverter {
    //Simplify fun NEED MODIFICATION
    fun convert(valute: Valute?, count: Double):Double{
        if (valute != null){
            return count / (valute.value / valute.nominal)
        }

        return 0.0
    }
}