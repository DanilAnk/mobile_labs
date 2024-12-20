package com.example.lab4_2

import android.content.Intent
import android.os.Build.VERSION
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
    private val CHEAT_REQUEST_CODE = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.nextButton.visibility = View.INVISIBLE

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
            binding.cheatButton.visibility = View.VISIBLE
            binding.nextButton.visibility = View.INVISIBLE
        }

        binding.cheatButton.setOnClickListener {
            if (quizViewModel.canCheat()) {
            //val intent = Intent(this, CheatActivity::class.java)
            //startActivity(intent)

                val intent = Intent(this, CheatActivity::class.java)
                startActivityForResult(intent, CHEAT_REQUEST_CODE)


            } else {
                Toast.makeText(this, "Доступных подсказок нет!", Toast.LENGTH_SHORT).show()
            }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CHEAT_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                val useCheat = data?.getBooleanExtra("USE_CHEAT", false) ?: false

                if (useCheat && quizViewModel.canCheat()) {
                    quizViewModel.useCheat()
                    val answerIsTrue = quizViewModel.questionBank[quizViewModel.currentIndex].isAnswerTrue
                    Toast.makeText(this, "Правильный ответ: ${if (answerIsTrue) "True" else "False"}", Toast.LENGTH_LONG).show()
                    if (answerIsTrue) binding.falseButton.visibility = View.INVISIBLE
                    else binding.trueButton.visibility = View.INVISIBLE
                    binding.cheatButton.visibility = View.INVISIBLE

                } else {
                    Toast.makeText(this, "Вы исчерпали количество подсказок!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Подсказка отменена", Toast.LENGTH_SHORT).show()
            }
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
        binding.cheatButton.visibility = View.INVISIBLE
        binding.nextButton.visibility = View.VISIBLE

        if (quizViewModel.getAnswersCount() == quizViewModel.questionBank.size - 1) {
            Toast.makeText(this, "Правильные ответы: ${quizViewModel.getCorrectAnswersCount()}", Toast.LENGTH_LONG).show()
        }
    }

}