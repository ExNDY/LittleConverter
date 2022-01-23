package app.wcrtv.littleconverter.data.local.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.wcrtv.littleconverter.model.CbrResponse

@Dao
interface RoomLocalDataBaseDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertData(cbrResponseEntity: CbrResponse)

    @Query("SELECT * FROM CbrResponse")
    fun getAllData() : LiveData<CbrResponse>

}