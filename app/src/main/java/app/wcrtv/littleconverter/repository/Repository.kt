package app.wcrtv.littleconverter.repository

import app.wcrtv.littleconverter.data.network.RetrofitService
import app.wcrtv.littleconverter.data.network.model.Valute

class Repository(private val retrofitService: RetrofitService) : DataStoreDAO{
    //private val retrofitService = RetrofitService.getInstance()
    fun getLatestDailyRates() = retrofitService.getLatestDailyRates()


    override fun loadData() {
        TODO("Not yet implemented")
    }

    override fun restoreData(): ArrayList<Valute> {
        TODO("Not yet implemented")
    }

    override fun saveData(dataList: ArrayList<Valute>) {
        TODO("Not yet implemented")
    }

    override fun deleteData() {
        TODO("Not yet implemented")
    }

    override fun updateData(dataList: ArrayList<Valute>) {
        TODO("Not yet implemented")
    }
}