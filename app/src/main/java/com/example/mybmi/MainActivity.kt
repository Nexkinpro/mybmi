package com.example.mybmi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_bmi_layout)
        val etWeightId = findViewById<EditText>(R.id.et_weight_id)
        val etHeightId = findViewById<EditText>(R.id.et_height_id)
        val calId = findViewById<TextView>(R.id.calc_id)

        calId.setOnClickListener {
            val weightValue = etWeightId.text.toString()
            val heightValue = etHeightId.text.toString()
            if (validateInput(weightValue, heightValue)) {
                val bmi = weightValue.toFloat() / ((heightValue.toFloat() / 100) * (heightValue.toFloat() / 100))
                // get result with two decimal places
                val bmi2Digits = String.format("%.2f", bmi).toFloat()
                displayResult(bmi2Digits)
            }
        }

    }

     private fun displayResult(bmi: Float) {
        val resultDescription = findViewById<TextView>(R.id.result_id)
        val resultIndex = findViewById<TextView>(R.id.description_id)
        val info = findViewById<TextView>(R.id.info_id)

        resultDescription.text = bmi.toString()

        var resultText = ""
        var color = 0
        when {
            bmi < 18.50 -> {
                resultText = "Underweight"
                color = R.color.under_weight
                info.text = "(Underweight range is 16.0 – 16.9)"
            }

            bmi in 18.50..24.99 -> {
                resultText = "Healthy"
                color = R.color.normal
                info.text = "(Healthy range is 18.5 - 24.9)"
            }

            bmi in 25.00..29.99 -> {
                resultText = "Overweight"
                color = R.color.over_weight
                info.text = "(Overweight range is 25.0 – 29.9)"
            }

            bmi > 29.99 -> {
                resultText = "Obese"
                color = R.color.obese
                info.text = "(Obese range is 30.0 – 34.9)"
            }

        }
        resultDescription.setTextColor(ContextCompat.getColor(this, color))
        resultDescription.text = resultText


    }

    private fun validateInput(weightValue: String, heightValue: String): Boolean {
        return when {
            weightValue.isNullOrEmpty() -> {
                Toast.makeText(this, "Weight value is empty", Toast.LENGTH_LONG).show()
                return false
            }
            heightValue.isNullOrEmpty() -> {
                Toast.makeText(this, "Height value is empty", Toast.LENGTH_LONG).show()
                return false
            }

            else -> {
                return true
            }

        }
    }
}