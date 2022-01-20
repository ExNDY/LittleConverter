package app.wcrtv.littleconverter.repository

import app.wcrtv.littleconverter.data.network.model.Valute

interface DataStoreDAO {
    fun loadData()
    fun restoreData():ArrayList<Valute>
    fun saveData(dataList: ArrayList<Valute>)
    fun deleteData()
    fun updateData(dataList: ArrayList<Valute>)
}