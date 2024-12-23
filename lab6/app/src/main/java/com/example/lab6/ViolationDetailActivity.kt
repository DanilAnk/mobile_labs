package com.example.lab6

import Violation
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ViolationDetailActivity : AppCompatActivity() {

    private lateinit var titleEditText: EditText
    private lateinit var dateTextView: TextView
    private lateinit var resolvedCheckBox: CheckBox
    private lateinit var backButton: Button
    private lateinit var selectSuspectButton: Button
    private lateinit var sendReportButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_violation_detail)

        titleEditText = findViewById(R.id.violation_title_edit)
        dateTextView = findViewById(R.id.violation_date)
        resolvedCheckBox = findViewById(R.id.resolved_checkbox)
        backButton = findViewById(R.id.back_button)
        selectSuspectButton = findViewById(R.id.select_suspect_button)
        sendReportButton = findViewById(R.id.send_report_button)

        val violation = intent.getSerializableExtra("violation") as Violation


        titleEditText.setText(violation.title)
        dateTextView.text = violation.date
        resolvedCheckBox.isChecked = violation.isResolved


        backButton.setOnClickListener {
            finish()
        }


        selectSuspectButton.setOnClickListener {
            Toast.makeText(this, "Выберите подозреваемого", Toast.LENGTH_SHORT).show()
        }

        sendReportButton.setOnClickListener {
            Toast.makeText(this, "Отчёт отправлен", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
