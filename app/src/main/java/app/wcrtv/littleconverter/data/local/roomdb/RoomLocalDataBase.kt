package app.wcrtv.littleconverter.data.local.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import app.wcrtv.littleconverter.data.local.MapTypeConverter
import app.wcrtv.littleconverter.model.CbrResponse

@Database(entities = [CbrResponse::class], version = 1, exportSchema = false)
@TypeConverters(MapTypeConverter::class)
abstract class RoomLocalDataBase : RoomDatabase() {
    abstract fun localDataBase(): RoomLocalDataBaseDAO

    companion object {
        @Volatile
        private var INSTANCE: RoomLocalDataBase? = null

        fun getLocalData(context: Context) : RoomLocalDataBase{
            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {
                INSTANCE = Room
                    .databaseBuilder(context, RoomLocalDataBase::class.java, "LITTLE_CONVERTER_DATABASE")
                    .fallbackToDestructiveMigration()
                    .build()
            }

            return INSTANCE!!
        }
    }
}