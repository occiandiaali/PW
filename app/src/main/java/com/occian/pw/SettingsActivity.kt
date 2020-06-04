package com.occian.pw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val actionBar = supportActionBar
        actionBar?.title = "Settings"
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
