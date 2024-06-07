package com.example.calcumulator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.example.calcumulator.R

class MainActivity : ComponentActivity() {

    private var firstNumber: Double? = null
    private var secondNumber: Double? = null
    private var operation: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Button Operation Listeners
        findViewById<Button>(R.id.ac).setOnClickListener { clearAll() }
        findViewById<Button>(R.id.slh).setOnClickListener { switchSign() }
        findViewById<Button>(R.id.mod).setOnClickListener { setOperation("%") }
        findViewById<Button>(R.id.div).setOnClickListener { setOperation("/") }
        findViewById<Button>(R.id.mult).setOnClickListener { setOperation("*") }
        findViewById<Button>(R.id.minus).setOnClickListener { setOperation("-") }
        findViewById<Button>(R.id.plus).setOnClickListener { setOperation("+") }
        findViewById<Button>(R.id.dot).setOnClickListener { appendDecimal() }
        findViewById<Button>(R.id.equal).setOnClickListener { calculateResult() }

        // Number Button listeners
        val numberButtons = arrayOf(
            R.id.zero, R.id.one, R.id.two, R.id.three, R.id.four,
            R.id.five, R.id.six, R.id.seven, R.id.eight, R.id.nine
        )
        for (id in numberButtons) {
            findViewById<Button>(id).setOnClickListener { appendNumber((it as Button).text.toString()) }
        }
    }

    private fun clearAll() {
        firstNumber = null
        secondNumber = null
        operation = null
        findViewById<TextView>(R.id.result).text = "0.0"
    }

    private fun switchSign() {
        val currentText = findViewById<TextView>(R.id.result).text.toString()
        if (currentText.isNotEmpty() && currentText != "0.0") {
            findViewById<TextView>(R.id.result).text =
                if (currentText.startsWith("-")) currentText.substring(1) else "-$currentText"
        }
    }

    private fun appendNumber(number: String) {
        val currentText = findViewById<TextView>(R.id.result).text.toString()
        findViewById<TextView>(R.id.result).text =
            if (currentText == "0.0") number else currentText + number
    }

    private fun appendDecimal() {
        val currentText = findViewById<TextView>(R.id.result).text.toString()
        if (!currentText.contains(".")) {
            findViewById<TextView>(R.id.result).text = currentText + "."
        }
    }

    private fun setOperation(op: String) {
        val resultTextView = findViewById<TextView>(R.id.result)
        val currentText = resultTextView.text.toString()

        // If firstNumber is not set, set it
        if (firstNumber == null) {
            firstNumber = currentText.toDoubleOrNull() ?: 0.0
        } else {
            // If there's an existing operation, set secondNumber and calculate
            if (operation != null) {
                secondNumber = currentText.toDoubleOrNull() ?: 0.0
                calculateResult()
                firstNumber = resultTextView.text.toString().toDoubleOrNull() ?: 0.0
            }
        }

        // Set the new operation
        operation = op

        // Clear the result display for the next input
        resultTextView.text = "0.0"
    }

    private fun calculateResult() {
        val resultTextView = findViewById<TextView>(R.id.result)
        val currentText = resultTextView.text.toString()

        if (firstNumber != null && operation != null) {
            secondNumber = currentText.toDoubleOrNull() ?: 0.0
            val result = when (operation) {
                "+" -> firstNumber!! + secondNumber!!
                "-" -> firstNumber!! - secondNumber!!
                "*" -> firstNumber!! * secondNumber!!
                "/" -> {
                    if (secondNumber == 0.0) {
                        "Error: Division by zero"
                    } else {
                        firstNumber!! / secondNumber!!
                    }
                }
                "%" -> firstNumber!! % secondNumber!!
                else -> 0.0  // Handle default case with a valid Double value
            }

            // Update the result TextView with the calculated result
            resultTextView.text = result.toString()

            // Update firstNumber with the calculated result
            firstNumber = result.toString().toDoubleOrNull() ?: 0.0  // Convert result String to Double

            // Reset secondNumber and operation for next operation
            secondNumber = null
            operation = null
        }
    }
}