package com.example.lab6


import Violation
import ViolationAdapter
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var violationsListView: ListView
    private lateinit var addViolationButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        violationsListView = findViewById(R.id.violations_list)
        addViolationButton = findViewById(R.id.add_violation_button)

        val violations = mutableListOf<Violation>() // Список нарушений



        // Адаптер для списка нарушений
        val adapter = ViolationAdapter(this, violations)
        violationsListView.adapter = adapter

        addViolationButton.setOnClickListener {
            val newViolation = Violation("Новое нарушение", "22-12-2024", false)
            violations.add(newViolation)
            adapter.notifyDataSetChanged()
        }

    }
}


