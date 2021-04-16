package com.example.databasequizproject

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.core.database.getStringOrNull
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper


class DatabaseOpenHelper(private val context: Context) : SQLiteAssetHelper(
    context, DATABASE_NAME, null,
    DATABASE_VERSON
) {
    lateinit var db: SQLiteDatabase

    companion object {
        val DATABASE_NAME = "database.sqlite"
        val DATABASE_VERSON: Int = 1;
    }


    fun open() {
        this.db = writableDatabase;
    }

    override fun close() {
        if (db != null) this.db.close()

    }

    fun getQuestion(): MutableList<Question> {
        var c: Cursor? = null
        c = db.rawQuery("select * from quiz", null)
        var questionList: MutableList<Question> = mutableListOf()
        if (c.moveToFirst()) {
            do {
                var qustion = Question()
                qustion.qId = c.getInt(c.getColumnIndex("q_id"))
                qustion.question = c.getStringOrNull(c.getColumnIndexOrThrow("question")).toString()
                qustion.opt1 = c.getStringOrNull(c.getColumnIndexOrThrow("opt1")).toString()
                qustion.opt2 = c.getStringOrNull(c.getColumnIndexOrThrow("opt2")!!).toString()
                qustion.opt3 = c.getStringOrNull(c.getColumnIndexOrThrow("opt3")!!).toString()
                qustion.opt4 = c.getStringOrNull(c.getColumnIndexOrThrow("opt4")).toString()
                qustion.answer = c.getStringOrNull(c.getColumnIndexOrThrow("opt1")).toString()
                questionList.add(qustion)
            } while (c.moveToNext())


        }
        return questionList
    }

}