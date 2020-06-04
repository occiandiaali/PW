package com.occian.pw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class HelpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)

        val actionBar = supportActionBar
        actionBar?.title = "Help"
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
