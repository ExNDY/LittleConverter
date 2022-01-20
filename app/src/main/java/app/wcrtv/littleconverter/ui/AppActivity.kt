package app.wcrtv.littleconverter.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.wcrtv.littleconverter.databinding.AppActivityBinding

class AppActivity : AppCompatActivity() {
    private lateinit var binding:AppActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = AppActivityBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }
}