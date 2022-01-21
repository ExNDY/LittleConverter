package app.wcrtv.littleconverter.data.network

import app.wcrtv.littleconverter.data.network.model.CbrResponse
import app.wcrtv.littleconverter.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {
    @GET("daily_json.js")
    fun getLatestDailyRates(): Call<CbrResponse?>

    companion object {
        private var retrofitService: RetrofitService? = null

        fun getInstance() : RetrofitService {
            if (retrofitService == null){
                val client = OkHttpClient.Builder()
                    .build()

                val retrofit = Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                retrofitService = retrofit.create(RetrofitService::class.java)
            }

            return retrofitService!!
        }
    }
}