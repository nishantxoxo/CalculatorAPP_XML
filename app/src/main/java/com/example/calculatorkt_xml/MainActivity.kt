package com.example.calculatorkt_xml

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private var tvInput: TextView? = null


    var lastNumeric : Boolean = false;
    var lastDot : Boolean = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        tvInput = findViewById(R.id.tvInput)
    }

    fun onDigit(view: View){
//         Toast.makeText(this, "button clicked", Toast.LENGTH_LONG).show()
     tvInput?.append((view as Button).text)
        lastNumeric = true
//        lastDot = false
    }


    fun onClear(view: View){
        tvInput?.text = ""
    }
    fun ondecimal(view: View){
        if(lastNumeric && !lastDot){
            tvInput?.append(".")
            lastNumeric =false
            lastDot = true
        }
    }


    fun onOperator(view: View){
        tvInput?.text?.let {

            if(lastNumeric && !isoperatorAdded(it.toString())){
                tvInput?.append((view as Button).text)
            }

        }

    }

    private fun isoperatorAdded(value: String) : Boolean{
        return if(value.startsWith("-")){
            false
        }
        else{
            value.contains("/") ||
                    value.contains("*") ||
                    value.contains("+") ||
                    value.contains("-")
        }
    }
}