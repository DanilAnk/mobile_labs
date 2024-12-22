package com.example.lab5

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var hoursEditText: EditText
    private lateinit var discountSeekBar: SeekBar
    private lateinit var discountTextView: TextView
    private lateinit var calculateButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hoursEditText = findViewById(R.id.hoursEditText)
        discountSeekBar = findViewById(R.id.discountSeekBar)
        discountTextView = findViewById(R.id.discountTextView)
        calculateButton = findViewById(R.id.calculateButton)

        discountSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                discountTextView.text = "Скидка: $progress%"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        calculateButton.setOnClickListener {
            val hours = hoursEditText.text.toString().toIntOrNull() ?: 0
            val discount = discountSeekBar.progress
            val cost = calculateCost(hours, discount)

            val intent = Intent(this, SecondActivity::class.java).apply {
                putExtra("PAYMENT", cost)
            }
            startActivity(intent)
        }
    }

    private fun calculateCost(hours: Int, discount: Int): Int {
        val ratePerHour = 2000
        val totalCost = ratePerHour * hours
        return totalCost - (totalCost * discount / 100)
    }
}