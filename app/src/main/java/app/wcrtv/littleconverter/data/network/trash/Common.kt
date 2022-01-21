package app.wcrtv.littleconverter.data.network.trash

object Common {
    val retrofitServices: RetrofitServices
        get() = RetrofitClient.getClient().create(RetrofitServices::class.java)

}