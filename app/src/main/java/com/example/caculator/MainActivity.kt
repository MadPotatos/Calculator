package com.example.caculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.caculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var lastNum : Boolean = false
    private var lastDot : Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.btnClear.setOnClickListener {
            binding.tvInput.text = ""
        }
        binding.btnDot.setOnClickListener {
            if (lastNum && !lastDot) {
                binding.tvInput.append(".")
                lastNum = false
                lastDot = true
            }
        }
        binding.btnEqual.setOnClickListener{
            if(lastNum){
                var tvValue = binding.tvInput.text.toString()
                var prefix = ""
                try {
                    if(tvValue.startsWith("-")) {
                        prefix ="-"
                        tvValue = tvValue.substring(1)
                    }
                    if (tvValue.contains("-")) {
                        val splitValue = tvValue.split("-")
                        var first = splitValue[0]
                        var second = splitValue[1]
                        if(prefix.isNotEmpty()){
                            first = prefix + first
                        }
                        var result = first.toDouble() - second.toDouble()
                        binding.tvInput.text = result.toString()
                    }
                    else if (tvValue.contains("+")) {
                        val splitValue = tvValue.split("+")
                        var first = splitValue[0]
                        var second = splitValue[1]
                        if(prefix.isNotEmpty()){
                            first = prefix + first
                        }
                        var result = first.toDouble() + second.toDouble()
                        binding.tvInput.text = result.toString()
                    }
                    else if (tvValue.contains("/")) {
                        val splitValue = tvValue.split("/")
                        var first = splitValue[0]
                        var second = splitValue[1]
                        if(prefix.isNotEmpty()){
                            first = prefix + first
                        }
                        var result = first.toDouble() / second.toDouble()
                        binding.tvInput.text = result.toString()
                    }
                    else if (tvValue.contains("*")) {
                        val splitValue = tvValue.split("*")
                        var first = splitValue[0]
                        var second = splitValue[1]
                        if(prefix.isNotEmpty()){
                            first = prefix + first
                        }
                        var result = first.toDouble() * second.toDouble()
                        binding.tvInput.text = result.toString()
                    }
                }catch (e: ArithmeticException){
                    e.printStackTrace()
                }
            }
        }


    }
    fun onOperator(view : View){
        binding.tvInput.text?.let{
        if(lastNum && !isOperatorAdded(it.toString())){
            binding.tvInput.append((view as Button).text)
            lastNum = false
            lastDot = false
            }
        }
    }
    fun onDigit(view: View) {
        binding.tvInput.append((view as Button).text)
        lastNum = true
        lastDot = false
    }
    private fun isOperatorAdded(value: String) : Boolean{
        return if(value.startsWith("-")){
            false
        }else{
            value.contains("/") ||value.contains("+") ||value.contains("*") ||value.contains("-")
        }
    }

}