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
import androidx.core.widget.addTextChangedListener
import kotlinx.android.synthetic.main.activity_main.*

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
        floatingActionButton.setOnClickListener {
                val hashedVal = inputFld.hashCode()
                val randomStr = (1..len)
                    .map { i -> kotlin.random.Random.nextInt(0, poolOfChars.size) }
                    .map(poolOfChars::get)
                    .joinToString("")

                resultTextView.text = "$hashedVal$randomStr"
                resultTextView.visibility = View.VISIBLE
                floatingActionButton.isEnabled = false
        } // fab method

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
