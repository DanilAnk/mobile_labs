package com.example.lab5

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    private lateinit var paymentTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        paymentTextView = findViewById(R.id.paymentTextView)

        val payment = intent.getIntExtra("PAYMENT", 0)
        paymentTextView.text = "Оплатить = $payment рублей"
    }
}
