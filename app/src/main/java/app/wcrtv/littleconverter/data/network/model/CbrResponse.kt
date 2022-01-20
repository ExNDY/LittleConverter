package app.wcrtv.littleconverter.data.network.model

import com.google.gson.annotations.SerializedName

data class CbrResponse(
    @SerializedName("Date")
    var date: String,
    @SerializedName("PreviousDate")
    var previousDate: String,
    @SerializedName("PreviousURL")
    var previousUrl: String,
    @SerializedName("Timestamp")
    var timestamp: String,
    @SerializedName("Valute")
    var valute: Map<String, Valute>
)
