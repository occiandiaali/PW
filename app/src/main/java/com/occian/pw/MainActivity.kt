package com.occian.pw

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder
import java.math.BigInteger
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets
import java.security.MessageDigest

class MainActivity : AppCompatActivity() {

    private val len = 7
    private val poolOfChars: List<Char>
        get() = ('a'..'z') + ('A'..'Z') + ('0'..'9')

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // disable fab until input field is typed in
        floatingActionButton.isEnabled = false
        inputFld.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                val inputVal: String = inputFld.text.toString().trim()
                floatingActionButton.isEnabled = inputVal.isNotEmpty()
            }
        })


        // fab takes user input and generates + displays 'hash'
//        floatingActionButton.setOnClickListener {
//                var hashedVal = inputFld.hashCode()
//
//                val randomStr = (1..len)
//                    .map { i -> kotlin.random.Random.nextInt(0, poolOfChars.size) }
//                    .map(poolOfChars::get)
//                    .joinToString("")
//                resultTextView.text = "$randomStr${hashedVal * 7}"
//                resultTextView.visibility = View.VISIBLE
//                floatingActionButton.isEnabled = false
//        } // fab method

        floatingActionButton.setOnClickListener {
            val uInput = inputFld.toString()
            val md: MessageDigest = MessageDigest.getInstance("SHA-256")
            val hashInBytes = md.digest(uInput.toByteArray(StandardCharsets.UTF_8))

            val sb = StringBuilder()
            for (b: Byte in hashInBytes) {
                sb.append(String.format("%02x", b))
            }
            resultTextView.text = sb.toString()
            resultTextView.visibility = View.VISIBLE
            floatingActionButton.isEnabled = false
        }



    } // on create



    // clear input field and display text
    fun refresh() {
        resultTextView.text = null
        inputFld.text = null
    }

    // menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId) {
            R.id.refresh -> {
                refresh()
                true
            }
            R.id.settings -> {
                val settingsIntent = Intent(this@MainActivity, SettingsActivity::class.java)
                startActivity(settingsIntent)
                true
            }
            R.id.help -> {
                val helpIntent = Intent(this@MainActivity, HelpActivity::class.java)
                startActivity(helpIntent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


} // class


