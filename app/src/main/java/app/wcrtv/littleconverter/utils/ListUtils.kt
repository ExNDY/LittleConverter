package app.wcrtv.littleconverter.utils

import app.wcrtv.littleconverter.data.network.model.Valute

class ListUtils {
    fun convertMapToArrayList(map: Map<String, Valute>): ArrayList<Valute> {
        return ArrayList(map.values)
    }
}