package com.example.lab8.ui

import TaskAdapter
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lab8.R
import com.example.lab8.model.Priority
import com.example.lab8.model.Task

class TaskHolder (itemView: View, private val context: Context, private val adapter: TaskAdapter) : RecyclerView.ViewHolder(itemView){
    private val taskContent: TextView = itemView.findViewById(R.id.taskContent)
    private val priorityCircle: FrameLayout = itemView.findViewById(R.id.priorityCircle)
    private val priorityNumber: TextView = itemView.findViewById(R.id.priorityNumber)

    fun bind(task: Task){
        taskContent.text = task.content
        when (task.priority) {
            Priority.HIGH.level -> priorityCircle.backgroundTintList = ColorStateList.valueOf(Color.RED)
            Priority.MEDIUM.level -> priorityCircle.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#FFA500")) // Оранжевый
            else -> priorityCircle.backgroundTintList = ColorStateList.valueOf(Color.YELLOW)
        }
        priorityNumber.text = task.priority.toString()
    }
}