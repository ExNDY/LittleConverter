package app.wcrtv.littleconverter.repository

import app.wcrtv.littleconverter.data.network.Common
import app.wcrtv.littleconverter.model.DataJson
import app.wcrtv.littleconverter.model.Valute
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository: DataStoreDAO{
    private val mService = Common.retrofitServices

    override fun loadData():DataJson? {
        var dataJson: DataJson? = null

        mService.getLatestRates().enqueue(object : Callback<DataJson?> {
            override fun onResponse(call: Call<DataJson?>, response: Response<DataJson?>) {
                if (response.isSuccessful) {
                    dataJson = response.body()
                }
            }

            override fun onFailure(call: Call<DataJson?>, t: Throwable) {
                t.printStackTrace()
            }
        })

        return dataJson
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