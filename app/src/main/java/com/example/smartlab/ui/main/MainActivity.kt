package com.example.smartlab.ui.main

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.example.smartlab.R
import kotlinx.coroutines.flow.collect
import java.util.prefs.Preferences

class MainActivity : AppCompatActivity() {
    private val sharedPreferences: SharedPreferences by lazy {
        this.getSharedPreferences(
            "PREFS1",
            Context.MODE_PRIVATE
        )
    }
    private val viewModel: MainViewModel by lazy {
        MainViewModel(sharedPreferences)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        lifecycleScope.launchWhenStarted {
            viewModel.isFirstLaunch.collect{
                if (it) {
                    sharedPreferences.edit().putBoolean("BOOLEAN", false).apply()
                    Handler(Looper.getMainLooper()).postDelayed({
                        navController.navigate(R.id.action_launchFragment_to_onBoardFragment)
                    }, 3000)
                } else {
                    Handler(Looper.getMainLooper()).postDelayed({
                        navController.navigate(R.id.action_launchFragment_to_emailFragment)
                    }, 3000)
                }
            }
        }

    }
}