package app.wcrtv.littleconverter.ui.converter

import androidx.lifecycle.ViewModel
import app.wcrtv.littleconverter.model.DataJson
import app.wcrtv.littleconverter.data.network.Common
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ConverterViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    fun initData(){
        val mService = Common.retrofitServices

        mService.getLatestRates().enqueue(object : Callback<DataJson?> {
            override fun onResponse(call: Call<DataJson?>, response: Response<DataJson?>) {
                if (response.isSuccessful) {
                    var dataJson: DataJson? = response.body()

                }
            }

            override fun onFailure(call: Call<DataJson?>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}