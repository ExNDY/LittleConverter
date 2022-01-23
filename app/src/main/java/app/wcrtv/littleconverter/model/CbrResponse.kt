package app.wcrtv.littleconverter.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import app.wcrtv.littleconverter.data.local.MapTypeConverter
import app.wcrtv.littleconverter.model.Valute
import com.google.gson.annotations.SerializedName

@Entity(tableName = "CbrResponse")
data class CbrResponse(
    @ColumnInfo(name = "date")
    @SerializedName("Date")
    var date: String,
    @ColumnInfo(name = "previousdate")
    @SerializedName("PreviousDate")
    var previousDate: String,
    @ColumnInfo(name = "previousurl")
    @SerializedName("PreviousURL")
    var previousUrl: String,
    @ColumnInfo(name = "timestamp")
    @SerializedName("Timestamp")
    var timestamp: String,
    @ColumnInfo(name = "valute")
    @SerializedName("Valute")
    @TypeConverters(MapTypeConverter::class)
    var valute: Map<String, Valute>
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null
}
