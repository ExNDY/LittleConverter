package app.wcrtv.littleconverter.utils

import app.wcrtv.littleconverter.model.Valute

object ListUtils {
    fun convertMapToList(map: Map<String, Valute>): ArrayList<Valute> {
        return ArrayList(map.values)
    }
}