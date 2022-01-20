package app.wcrtv.littleconverter.data.network

import app.wcrtv.littleconverter.data.network.model.CbrResponse
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitServices {
    @GET("daily_json.js")
    fun getLatestRates(): Call<CbrResponse?>
}