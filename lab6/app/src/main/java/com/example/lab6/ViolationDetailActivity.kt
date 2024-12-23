package com.example.lab6

import android.app.Activity
import com.example.lab6.Violation
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class ViolationDetailActivity : AppCompatActivity() {

    private lateinit var titleEditText: EditText
    private lateinit var dateTextView: TextView
    private lateinit var resolvedCheckBox: CheckBox
    private lateinit var backButton: Button
    private lateinit var deleteButton: Button
    private lateinit var sendReportButton: Button

    private lateinit var dao: ViolationDao
    private lateinit var violation: Violation


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_violation_detail)

        titleEditText = findViewById(R.id.violation_title_edit)
        dateTextView = findViewById(R.id.violation_date)
        resolvedCheckBox = findViewById(R.id.resolved_checkbox)
        backButton = findViewById(R.id.back_button)
        deleteButton = findViewById(R.id.delete_button)
        sendReportButton = findViewById(R.id.send_report_button)

        violation = intent.getSerializableExtra("violation") as Violation
        dao = AppDatabase.getDatabase(this).violationDao()

        titleEditText.setText(violation.title)
        dateTextView.text = violation.date
        resolvedCheckBox.isChecked = violation.isResolved

        backButton.setOnClickListener {
            finish()
        }

        deleteButton.setOnClickListener {
            lifecycleScope.launch {
                dao.delete(violation)
                setResult(Activity.RESULT_OK)
                finish()
            }
        }

        sendReportButton.setOnClickListener {
            lifecycleScope.launch {
                violation.title = titleEditText.text.toString()
                violation.isResolved = resolvedCheckBox.isChecked
                dao.update(violation)
                setResult(Activity.RESULT_OK)
                finish()
            }
        }
    }

}
