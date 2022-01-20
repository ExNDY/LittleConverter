package app.wcrtv.littleconverter.data.network.model

import com.google.gson.annotations.SerializedName

data class Valute(
    @SerializedName("ID")
    var id: String,
    @SerializedName("NumCode")
    var numCode: Int,
    @SerializedName("CharCode")
    var charCode: String,
    @SerializedName("Nominal")
    var nominal: Int,
    @SerializedName("Name")
    var name: String,
    @SerializedName("Value")
    var value: Double,
    @SerializedName("Previous")
    var previous: Double
)
