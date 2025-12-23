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


    fun onEqual(view: View){
        if(lastNumeric){
            var tvval = tvInput?.text.toString()
            var prefix = ""
            try {
                if (tvval.startsWith("-")){
                    prefix = "-"
                    tvval = tvval.substring(1)
                }




                if(tvval.contains("-")){
                    val splitval = tvval.split("-")
                var one = splitval[0]
                var two = splitval[1]


                if(prefix.isNotEmpty()){
                    one = prefix + one
                }

                tvInput?.text =  removezeroafterdot((one.toDouble() - two.toDouble()).toString())
                }
                else if(tvval.contains("+")){
                    val splitval = tvval.split("+")
                    var one = splitval[0]
                    var two = splitval[1]


                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    tvInput?.text =  removezeroafterdot((one.toDouble() + two.toDouble()).toString())
                }else if(tvval.contains("/")){
                    val splitval = tvval.split("/")
                    var one = splitval[0]
                    var two = splitval[1]


                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    tvInput?.text =  removezeroafterdot((one.toDouble() / two.toDouble()).toString())
                }else if(tvval.contains("*")){
                    val splitval = tvval.split("*")
                    var one = splitval[0]
                    var two = splitval[1]


                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    tvInput?.text =  removezeroafterdot((one.toDouble() * two.toDouble()).toString())
                }
            }
            catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    private fun removezeroafterdot(result: String) : String{
        var valu = result
        if(result.contains(".0")){
            valu = result.substring(0, result.length - 2)
        }

        return valu
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