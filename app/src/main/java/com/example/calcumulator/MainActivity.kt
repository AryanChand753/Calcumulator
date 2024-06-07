package com.example.calcumulator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.calcumulator.ui.theme.CalcumulatorTheme
import android.app.AppCompatActivity
import android.view.View // Needed for click listener interfaces (OnClickListener)
import android.widget.Button // Needed for Button class
import android.widget.TextView // Needed for TextView class

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
        findViewById<Button>(R.id.mod).setOnClickListener { operation = "%" }
        findViewById<Button>(R.id.div).setOnClickListener { operation = "/" }
        findViewById<Button>(R.id.mult).setOnClickListener { operation = "*"}
        findViewById<Button>(R.id.minus).setOnClickListener { operation = "-" }
        findViewById<Button>(R.id.plus).setOnClickListener { operation = "+" }
        findViewById<Button>(R.id.dot).setOnClickListener { appendDecimal() }
        findViewById<Button>(R.id.equal).setOnClickListener { calculateResult() }

        // Number Button listeners
        val numberButtons = arrayOf(
            R.id.zero, R.id.one, R.id.two, R.id.three, R.id.four, R.id.five, R.id.six, R.id.seven, R.id.eight, R.id.nine
        )
        for (id in numberButtons) {
            val button = findViewById<Button>(id)
            button.setOnClickListener { appendNumber(it.text.toString()) }
        }
    }

    private fun clearAll() {
        firstNumber = null
        secondNumber = null
        operation = null
        findViewById<TextView>(R.id.result).text = "0.0"
    }

    private fun switchSign() {  }

    private fun appendNumber(number: String) {  }

    private fun appendDecimal() {  }

    private fun calculateResult() {  }


}