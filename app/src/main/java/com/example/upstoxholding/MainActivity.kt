package com.example.upstoxholding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.upstoxholding.ui.main.HoldingsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, HoldingsFragment.newInstance())
                .commitNow()
        }
    }
}