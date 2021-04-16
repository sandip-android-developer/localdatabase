package com.example.quizapplication

import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import com.example.databasequizproject.R
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    var mLastClickStart: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        initView()
    }

    private fun initView() {
        var result = intent.getIntExtra("result", 0)
        textResult.text = "Your score is " + result.toString() + "/" + "400"

        btnStartAgain?.setOnClickListener {
            if (SystemClock.elapsedRealtime() - mLastClickStart < 1000) return@setOnClickListener
            mLastClickStart = SystemClock.elapsedRealtime()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

}