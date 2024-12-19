package com.example.lab4

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


data class Question(val text: String, val isAnswerTrue: Boolean)

class MainActivity : AppCompatActivity() {

    private lateinit var questionTextView: TextView
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button

    private var currentIndex = 0
    private var correctAnswersCount = 0

    private val questionBank = arrayOf(
        Question("Столица Франции — Париж?", true),
        Question("Сахара — это океан?", false),
        Question("Россия — самая большая страна в мире?", true),
        Question("Канада находится на юге от Соединенных Штатов?", false),
        Question("Столица Японии — Токио?", true),
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        questionTextView = findViewById(R.id.question_text_view)
        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)

        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt("currentIndex", 0)
            correctAnswersCount = savedInstanceState.getInt("correctAnswersCount", 0)
        }

        updateQuestion()

        trueButton.setOnClickListener {
            checkAnswer(true)
        }

        falseButton.setOnClickListener {
            checkAnswer(false)
        }

        nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("currentIndex", currentIndex)
        outState.putInt("correctAnswersCount", correctAnswersCount)
    }

    private fun updateQuestion() {
        val questionText = questionBank[currentIndex].text
        questionTextView.text = questionText

        if (currentIndex == questionBank.size - 1) {
            nextButton.isEnabled = false
            nextButton.visibility = View.INVISIBLE
        } else {
            nextButton.isEnabled = true
            nextButton.visibility = View.VISIBLE
        }

        trueButton.visibility = View.VISIBLE
        falseButton.visibility = View.VISIBLE
    }

    private fun checkAnswer(userPressedTrue: Boolean) {
        val answerIsTrue = questionBank[currentIndex].isAnswerTrue
        if (userPressedTrue == answerIsTrue) {
            correctAnswersCount++
            Toast.makeText(this, "Правильный ответ!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Не правильный ответ!", Toast.LENGTH_SHORT).show()
        }

        trueButton.visibility = View.INVISIBLE
        falseButton.visibility = View.INVISIBLE

        if (currentIndex == questionBank.size - 1) {
            Toast.makeText(this, "Правильных ответов: $correctAnswersCount", Toast.LENGTH_LONG).show()
        }

    }

}