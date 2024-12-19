package com.example.lab4_2

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.lab4_2.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val quizViewModel: QuizViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        quizViewModel.currentQuestionText.observe(this) { question ->
            binding.questionTextView.text = question
        }

        binding.trueButton.setOnClickListener {
            checkUserAnswer(true)
        }

        binding.falseButton.setOnClickListener {
            checkUserAnswer(false)
        }

        binding.nextButton.setOnClickListener {
            quizViewModel.nextQuestion()

            if (quizViewModel.getAnswersCount() == quizViewModel.questionBank.size - 1) {
                binding.nextButton.isEnabled = false
                binding.nextButton.visibility = View.INVISIBLE
            } else {
                binding.nextButton.isEnabled = true
                binding.nextButton.visibility = View.VISIBLE
            }

            binding.trueButton.visibility = View.VISIBLE
            binding.falseButton.visibility = View.VISIBLE
        }
    }

    private fun checkUserAnswer(isTrue: Boolean) {
        if (quizViewModel.checkAnswer(isTrue)) {
            Toast.makeText(this, "Правильный ответ!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Неправильный ответ!", Toast.LENGTH_SHORT).show()
        }
        binding.trueButton.visibility = View.INVISIBLE
        binding.falseButton.visibility = View.INVISIBLE

        if (quizViewModel.getAnswersCount() == quizViewModel.questionBank.size - 1) {
            Toast.makeText(this, "Правильные ответы: ${quizViewModel.getCorrectAnswersCount()}", Toast.LENGTH_LONG).show()
        }
    }

}