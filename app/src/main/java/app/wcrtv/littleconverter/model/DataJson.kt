package app.wcrtv.littleconverter.model

import com.google.gson.annotations.SerializedName

data class DataJson(
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
