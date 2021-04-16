package com.example.quizapplication

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.os.SystemClock
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.databasequizproject.DatabaseOpenHelper
import com.example.databasequizproject.Question
import com.example.databasequizproject.R
import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity : AppCompatActivity() {
    var cTimer: CountDownTimer? = null
    var seconds: Int = 0
    var questionList: MutableList<Question> = mutableListOf()
    var db: DatabaseOpenHelper? = null
    var marks: Int = 0
    var mLastClickNext: Long = 0
    var mLastClickSkip: Long = 0
    var sAnswer: String = ""
    var cAnswer: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        initView()
        actionLisner()
    }

    private fun actionLisner() {
        buttonSkip?.setOnClickListener {
            if (SystemClock.elapsedRealtime() - mLastClickSkip < 1000) return@setOnClickListener
            mLastClickSkip = SystemClock.elapsedRealtime()
            marks -= 5
            if (viewPagerQuiz.currentItem == questionList.size - 1) {
                val intent = Intent(this@QuizActivity, ResultActivity::class.java)
                intent.putExtra("result", marks)
                startActivity(intent)
                finish()
            } else {
                viewPagerQuiz.setCurrentItem(viewPagerQuiz.currentItem + 1, true)
                cancelTimer()
                CountDown(30000, 1000)
            }

        }
        buttonNext?.setOnClickListener {
            if (SystemClock.elapsedRealtime() - mLastClickNext < 1000) return@setOnClickListener
            mLastClickNext = SystemClock.elapsedRealtime()

            if(sAnswer.equals(""))
            {
                Toast.makeText(this, "Please choose one answer!!",
                    Toast.LENGTH_SHORT).show();
                return@setOnClickListener
            }
            if (sAnswer.equals(cAnswer)) marks += 20
            else marks -= 10
            if (viewPagerQuiz.currentItem == questionList.size - 1) {
                val intent = Intent(this@QuizActivity, ResultActivity::class.java)
                intent.putExtra("result", marks)
                startActivity(intent)
                finish()
            } else {
                viewPagerQuiz.setCurrentItem(viewPagerQuiz.currentItem + 1, true)
                cancelTimer()
                CountDown(30000, 1000)
            }
            sAnswer = ""
            cAnswer = ""

        }

    }

    private fun initView() {

        db = DatabaseOpenHelper(this)
        db!!.open()

        questionList.addAll(db!!.getQuestion())
        var adapter = QuizAdapter(this, questionList, object : QuizAdapter.OnItemOptionClick {
            override fun OnItemOptionClick(selectedAnswer: String, correctAnswer: String) {
                sAnswer = selectedAnswer
                cAnswer = correctAnswer
            }
        })
        viewPagerQuiz.setAdapter(adapter);
        viewPagerQuiz.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                cancelTimer()
                CountDown(30000, 1000)
            }

            override fun onPageSelected(position: Int) {}

            override fun onPageScrollStateChanged(state: Int) {}
        })

    }

    private fun CountDown(startTime: Long, interval: Long) {
        cTimer = object : CountDownTimer(startTime, interval) {
            override fun onTick(millisUntilFinished: Long) {
                seconds = 20 - ((millisUntilFinished / interval) % 60).toInt()
                textTimer?.text =
                    ((millisUntilFinished / interval) % 60).toString() + " " + "Second Left"

            }

            override fun onFinish() {
                println("Finsih--")
                cancelTimer()
                if (viewPagerQuiz.currentItem == questionList.size - 1) {
                    val intent = Intent(this@QuizActivity, ResultActivity::class.java)
                    intent.putExtra("result", marks)
                    startActivity(intent)
                    finish()
                } else {
                    marks -= 5
                    viewPagerQuiz.setCurrentItem(viewPagerQuiz.currentItem + 1, true)
                }

            }
        }
        cTimer!!.start()
    }

    fun cancelTimer() {
        if (cTimer != null)
            cTimer!!.cancel()
    }
}