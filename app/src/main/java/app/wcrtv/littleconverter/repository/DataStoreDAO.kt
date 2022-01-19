package app.wcrtv.littleconverter.repository

import app.wcrtv.littleconverter.model.Valute

interface DataStoreDAO {
    fun restoreData():ArrayList<Valute>
    fun saveData(dataList: ArrayList<Valute>)
    fun deleteData()
    fun updateData(dataList: ArrayList<Valute>)
}