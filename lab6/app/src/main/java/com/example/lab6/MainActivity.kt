package com.example.lab6



import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var violationsListView: ListView
    private lateinit var addViolationButton: Button

    private lateinit var dao: ViolationDao
    private lateinit var database: AppDatabase
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ViolationAdapter

    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = AppDatabase.getDatabase(this)
        dao = database.violationDao()

        recyclerView = findViewById(R.id.violations_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = ViolationAdapter(emptyList()) { violation ->
            val intent = Intent(this, ViolationDetailActivity::class.java).apply {
                putExtra("violation", violation)
            }
            activityResultLauncher.launch(intent)
        }

        recyclerView.adapter = adapter


        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                loadViolations()
            }
        }

        loadViolations()

        findViewById<Button>(R.id.add_violation_button).setOnClickListener {
            val newViolation = Violation(title = "Новое нарушение", date = "22-12-2024", isResolved = false, name = "noName")
            lifecycleScope.launch {
                dao.insert(newViolation)
                loadViolations()
            }
        }

    }

    private fun loadViolations() {
        lifecycleScope.launch {
            val violationsList = database.violationDao().getAllViolations()
            adapter.updateData(violationsList)
        }
    }




}


