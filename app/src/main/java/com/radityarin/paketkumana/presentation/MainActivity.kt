package com.radityarin.paketkumana.presentation

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.radityarin.paketkumana.R
import com.radityarin.paketkumana.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        if (Build.VERSION.SDK_INT < 16) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN


        setContentView(view)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController


//        navController.addOnDestinationChangedListener { _, destination, _ ->
//            if (destination.id == androidx.navigation.ui.R.id.movieFragment || destination.id == androidx.navigation.ui.R.id.tvShowFragment || destination.id == androidx.navigation.ui.R.id.favoriteFragment) {
//                binding.toolbar.visibility = View.VISIBLE
//                navView.visibility = View.VISIBLE
//            } else if (destination.id == androidx.navigation.ui.R.id.settingFragment) {
//                binding.toolbar.visibility = View.VISIBLE
//                navView.visibility = View.GONE
//            } else {
//                binding.toolbar.visibility = View.GONE
//                navView.visibility = View.GONE
//            }
//        }

//        appBarConfiguration = AppBarConfiguration(
//            setOf(
//                androidx.navigation.ui.R.id.movieFragment, androidx.navigation.ui.R.id.tvShowFragment, androidx.navigation.ui.R.id.favoriteFragment
//            )
//        )

//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)
    }

}