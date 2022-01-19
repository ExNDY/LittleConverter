package app.wcrtv.littleconverter.data.network

object Common {
    val retrofitServices:RetrofitServices
        get() = RetrofitClient.getClient().create(RetrofitServices::class.java)

}