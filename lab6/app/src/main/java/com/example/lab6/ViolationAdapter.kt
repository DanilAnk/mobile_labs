package com.example.lab6

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lab6.R
import com.example.lab6.Violation

class ViolationAdapter(
    private var violations: List<Violation>,
    private val onItemClick: (Violation) -> Unit
) : RecyclerView.Adapter<ViolationAdapter.ViolationViewHolder>() {

    class ViolationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.violation_title)
        val dateTextView: TextView = itemView.findViewById(R.id.violation_date)
        val statusImageView: ImageView = itemView.findViewById(R.id.violation_status_icon)


}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViolationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.violation_item, parent, false)
        return ViolationViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViolationViewHolder, position: Int) {
        val violation = violations[position]
        holder.titleTextView.text = violation.title
        holder.dateTextView.text = violation.date

        if (violation.isResolved) {
            holder.statusImageView.setImageResource(R.drawable.is_res) // Убедитесь, что имя файла совпадает
            holder.statusImageView.visibility = View.VISIBLE // Показываем изображение
        } else {
            holder.statusImageView.visibility = View.GONE // Скрываем изображение
        }

        holder.itemView.setOnClickListener {
            onItemClick(violation)
        }
    }

    override fun getItemCount(): Int = violations.size

    fun updateData(newViolations: List<Violation>) {
        violations = newViolations
        notifyDataSetChanged()
    }
}
