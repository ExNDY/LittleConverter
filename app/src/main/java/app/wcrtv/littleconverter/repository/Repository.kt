package app.wcrtv.littleconverter.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import app.wcrtv.littleconverter.data.network.Common
import app.wcrtv.littleconverter.data.network.model.CbrResponse
import app.wcrtv.littleconverter.data.network.model.Valute
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository: DataStoreDAO{
    private val mService = Common.retrofitServices
    private val _response = MutableLiveData<CbrResponse>()

    override fun loadData() {
        var cbrResponse: CbrResponse? = null

        mService.getLatestRates().enqueue(object : Callback<CbrResponse?> {
            override fun onResponse(call: Call<CbrResponse?>, response: Response<CbrResponse?>) {
                if (response.isSuccessful) {
                    _response.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<CbrResponse?>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    fun getLoadedData():LiveData<CbrResponse>{
        return _response
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