package app.wcrtv.littleconverter.repository

import app.wcrtv.littleconverter.model.DataJson
import app.wcrtv.littleconverter.model.Valute

interface DataStoreDAO {
    fun loadData(): DataJson?
    fun restoreData():ArrayList<Valute>
    fun saveData(dataList: ArrayList<Valute>)
    fun deleteData()
    fun updateData(dataList: ArrayList<Valute>)
}