package app.wcrtv.littleconverter.data.local

import androidx.room.TypeConverter
import app.wcrtv.littleconverter.model.Valute
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object MapTypeConverter {
    @TypeConverter
    @JvmStatic
    fun fromString(value: String): Map<String, Valute> {
        return Gson().fromJson(value, object : TypeToken<Map<String, Valute>>() {}.type)
    }

    @TypeConverter
    @JvmStatic
    fun fromStringMap(map: Map<String, Valute>?): String {
        return if (map == null) "" else Gson().toJson(map)
    }
}