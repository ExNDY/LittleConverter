package app.wcrtv.littleconverter.repository

import android.content.Context
import androidx.lifecycle.LiveData
import app.wcrtv.littleconverter.data.local.roomdb.RoomLocalDataBase
import app.wcrtv.littleconverter.data.local.roomdb.RoomLocalDataBaseDAO
import app.wcrtv.littleconverter.model.CbrResponse
import app.wcrtv.littleconverter.data.network.retrofit.RetrofitService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class Repository(private val retrofitService: RetrofitService) {
    //private val retrofitService = RetrofitService.getInstance()
    fun getLatestDailyRates() = retrofitService.getLatestDailyRates()

    companion object {
        var localDataBase: RoomLocalDataBase? = null

        var cbrResponse: LiveData<CbrResponse>? = null

        fun initializeDB(context: Context): RoomLocalDataBase {
            return RoomLocalDataBase.getLocalData(context)
        }

        fun insertData(context: Context, cbrResponse: CbrResponse) {
            localDataBase = initializeDB(context)

            CoroutineScope(IO).launch {
                localDataBase!!.localDataBase().InsertData(cbrResponse)
            }
        }

        fun getLocalData(context: Context) : LiveData<CbrResponse>?{
            localDataBase = initializeDB(context)

            cbrResponse = localDataBase!!.localDataBase().getAllData()

            return cbrResponse
        }
    }
}