import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.lab6.R
import java.io.Serializable


data class Violation(val title: String, val date: String, var isResolved: Boolean) : Serializable

class ViolationAdapter(context: Context, private val violations: List<Violation>) :
    ArrayAdapter<Violation>(context, 0, violations) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Получаем или создаем элемент списка
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.violation_item, parent, false)

        // Получаем текущую запись нарушения
        val violation = violations[position]

        // Находим элементы интерфейса
        val titleTextView = view.findViewById<TextView>(R.id.violation_title)
        val dateTextView = view.findViewById<TextView>(R.id.violation_date)
        val statusImageView = view.findViewById<ImageView>(R.id.violation_status_icon)

        // Устанавливаем данные в элементы интерфейса
        titleTextView.text = violation.title
        dateTextView.text = violation.date


        return view
    }
}
