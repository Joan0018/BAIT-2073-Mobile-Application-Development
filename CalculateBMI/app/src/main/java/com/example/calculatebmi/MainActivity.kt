package com.example.calculatebmi

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.calculatebmi.Helper.LocalHelper
import io.paperdb.Paper

class MainActivity : AppCompatActivity() {

    lateinit var welTxtView: TextView
    lateinit var weightTxtView: TextView
    lateinit var heightTxtView: TextView
    lateinit var nameTxtView: TextView
    lateinit var statusTxtView: TextView
    lateinit var btnEnter: Button
    lateinit var btnCal: Button


    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LocalHelper.onAttach(newBase, "en"))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.btnEnter)

        btn.setOnClickListener(){

            val intent = Intent(this, BMIActivity::class.java)

            val personName = findViewById<TextView>(R.id.UserName)

            intent.putExtra("personName", personName.text.toString())

            startActivity(intent)
        }



        //Init Paper first
        Paper.init(this)

        //Default language is English
        val language = Paper.book().read<String>("Language")
        if (language == null) Paper.book().write("language", "en")
        updateView(Paper.book().read<Any>("language") as String)

    }

    private fun updateView(lang: String) {
        val context = LocalHelper.setLocale(this, lang)
        val resources = context.resources

        welTxtView.text = "Welcome"
        weightTxtView.text = "Weight"
        heightTxtView.text = "Height"
        nameTxtView.text = "Name:"
        statusTxtView.text = "Status:"
        btnEnter.text = "Enter"
        btnCal.text = "Calculate"

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.language_en) {
            Paper.book().write("language", "en")
            updateView(Paper.book().read<Any>("language") as String)
        } else if (item.itemId == R.id.language_ja) {
            Paper.book().write("language", "ja")
            updateView(Paper.book().read<Any>("language") as String)
        }
        return true
    }

}