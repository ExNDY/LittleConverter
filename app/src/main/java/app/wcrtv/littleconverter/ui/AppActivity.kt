package app.wcrtv.littleconverter.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import app.wcrtv.littleconverter.R
import app.wcrtv.littleconverter.databinding.AppActivityBinding

class AppActivity : AppCompatActivity() {
    private lateinit var binding: AppActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = AppActivityBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.nav_ratelist, R.id.nav_converter
        ).build()

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        val navController = navHostFragment?.findNavController()
        if (navController != null) {
            NavigationUI.setupActionBarWithNavController(
                this, navController, appBarConfiguration
            )
        }

        if (navController != null) {
            NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)
        }

    }
}