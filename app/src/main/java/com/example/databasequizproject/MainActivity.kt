package com.example.quizapplication

import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import com.example.databasequizproject.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var mLastClickStart:Long=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initiView()
    }

    private fun initiView() {
        btnStartQuiz.setOnClickListener {
            if (SystemClock.elapsedRealtime() - mLastClickStart < 1000) return@setOnClickListener
            mLastClickStart = SystemClock.elapsedRealtime()
            val intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
        }
    }


}