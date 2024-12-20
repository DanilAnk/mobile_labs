package com.example.lab4_2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity

class CheatActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        val apiVersionTextView: TextView = findViewById(R.id.api_version_text_view)
        apiVersionTextView.text = "Android API Version: ${android.os.Build.VERSION.SDK_INT}"

        val useCheatButton: Button = findViewById(R.id.use_cheat_button)
        val cancelButton: Button = findViewById(R.id.cancel_button)

        useCheatButton.setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra("USE_CHEAT", true)
            setResult(RESULT_OK, resultIntent)
            finish() // Закрываем текущую активность
        }

        cancelButton.setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra("USE_CHEAT", false)
            setResult(RESULT_CANCELED, resultIntent)
            finish() // Закрываем текущую активность
        }
    }
}