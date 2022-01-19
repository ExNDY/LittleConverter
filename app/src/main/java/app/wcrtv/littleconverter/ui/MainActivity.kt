package app.wcrtv.littleconverter.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.wcrtv.littleconverter.R
import app.wcrtv.littleconverter.data.network.RetrofitServices

class MainActivity : AppCompatActivity() {
    private lateinit var mService: RetrofitServices
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}