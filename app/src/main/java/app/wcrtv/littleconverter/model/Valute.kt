package app.wcrtv.littleconverter.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "allValutes")
data class Valute(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "ID")
    @SerializedName("ID")
    @Expose
    var id: String,
    @ColumnInfo(name = "NumCode")
    @SerializedName("NumCode")
    @Expose
    var numCode: Int,
    @ColumnInfo(name = "CharCode")
    @SerializedName("CharCode")
    @Expose
    var charCode: String,
    @ColumnInfo(name = "Nominal")
    @SerializedName("Nominal")
    @Expose
    var nominal: Int,
    @ColumnInfo(name = "Name")
    @SerializedName("Name")
    @Expose
    var name: String,
    @ColumnInfo(name = "Value")
    @SerializedName("Value")
    @Expose
    var value: Double,
    @ColumnInfo(name = "Previous")
    @SerializedName("Previous")
    @Expose
    var previous: Double
) {
    fun getDifference(): Double {
        return value - previous
    }
}
