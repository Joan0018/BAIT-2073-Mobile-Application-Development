package com.example.calculatebmi

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class BMIActivity : AppCompatActivity() {

    var bmiIndex: Double = 0.00

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b_m_i)

       val personName = intent?.getStringExtra("personName")

        val tv = findViewById<TextView>(R.id.txtVName)

        tv.setText(personName)

        if(savedInstanceState != null){
            bmiIndex = savedInstanceState.getDouble("bmiIndex")

            val tvStatus = findViewById<TextView>(R.id.txtVStatus)

            tvStatus.setText(getStatus())
        }

        val btn = findViewById<Button>(R.id.btnCalculate)

        btn.setOnClickListener(){

            val height = findViewById<TextView>(R.id.txtHeight).text.toString()
            val weight = findViewById<TextView>(R.id.txtWeight).text.toString()
            val tvStatus = findViewById<TextView>(R.id.txtVStatus)

            bmiIndex = weight.toDouble() / (height.toDouble() * height.toDouble())

            tvStatus.setText(getStatus())
        }

    }

    override fun onSaveInstanceState(outState: Bundle){
        super.onSaveInstanceState(outState)

        outState.putDouble("bmiIndex",bmiIndex)
    }

    fun getStatus():String{

        if(bmiIndex < 10.5)
            return "UnderWeight"
        else if (bmiIndex < 24.9)
            return "Normal Weight"
        else if(bmiIndex < 29.9)
            return "OverWeight"
        else if(bmiIndex < 34.9)
            return "Obesity Class 1"
        else if (bmiIndex < 39.9)
            return "Obesity Class 11"
        else
            return "Obesity Class 111"
    }

}