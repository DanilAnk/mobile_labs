package com.example.lab4_2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class Question(val text: String, val isAnswerTrue: Boolean)

class QuizViewModel : ViewModel() {
    val questionBank = listOf(
        Question("Столица Франции — Париж?", true),
        Question("Сахара — это океан?", false),
        Question("Россия — самая большая страна в мире?", true),
        Question("Канада находится на юге от Соединенных Штатов?", false),
        Question("Столица Японии — Токио?", true),
    )

    private var currentIndex = 0
    private var correctAnswersCount = 0
    private val _currentQuestionText = MutableLiveData<String>()
    val currentQuestionText: LiveData<String> get() = _currentQuestionText

    init {
        updateQuestion()
    }

    fun updateQuestion() {
        _currentQuestionText.value = questionBank[currentIndex].text
    }

    fun checkAnswer(userPressedTrue: Boolean): Boolean {
        val answerIsTrue = questionBank[currentIndex].isAnswerTrue
        if (userPressedTrue == answerIsTrue) {
            correctAnswersCount++
            return true
        }
        return false
    }

    fun nextQuestion() {
        currentIndex = (currentIndex + 1) % questionBank.size
        updateQuestion()
    }

    fun getCorrectAnswersCount(): Int {
        return correctAnswersCount
    }

    fun getAnswersCount(): Int {
        return currentIndex
    }
}