package com.example.quizapplication

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.viewpager.widget.PagerAdapter
import com.example.databasequizproject.Question
import com.example.databasequizproject.R

class QuizAdapter(
    var context: QuizActivity, var result: List<Question>,
    var onItemOptionClick: OnItemOptionClick
) : PagerAdapter() {

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int {
        return result.size
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun instantiateItem(view: ViewGroup, position: Int): Any {
        var inflater = LayoutInflater.from(context)
        val slidingLayout = inflater.inflate(R.layout.item_quiz, view, false)!!
        var txtQuestion = slidingLayout.findViewById<TextView>(R.id.txtQuestion)

        var cbOption1 = slidingLayout.findViewById<CheckBox>(R.id.cbOption1)
        var cbOption2 = slidingLayout.findViewById<CheckBox>(R.id.cbOption2)
        var cbOption3 = slidingLayout.findViewById<CheckBox>(R.id.cbOption3)
        var cbOption4 = slidingLayout.findViewById<CheckBox>(R.id.cbOption4)


        /* if (result.get(position).opt1.equals("null") ||result.get(position).opt1.isNullOrEmpty())
             cbOption1.visibility = View.GONE
         else cbOption1.visibility = View.VISIBLE
         if (result.get(position).opt2.equals("null") ||result.get(position).opt2.isNullOrEmpty())
             cbOption2.visibility = View.GONE
         else cbOption2.visibility = View.VISIBLE

         if (result.get(position).opt3.equals("null") ||result.get(position).opt3.isNullOrEmpty())
             cbOption3.visibility = View.GONE
         else cbOption3.visibility = View.VISIBLE

         if (result.get(position).opt4.equals("null") || result.get(position).opt4 == null||result.get(position).opt4.isNullOrEmpty())
             cbOption4.visibility = View.GONE
         else cbOption4.visibility = View.VISIBLE*/
        if (Math.random() % 2 == 0.0) {
            cbOption1.text = handleVisibityForNull(result.get(position).opt2, cbOption1)
            cbOption2.text = handleVisibityForNull(result.get(position).opt3, cbOption2)
            cbOption3.text = handleVisibityForNull(result.get(position).opt4, cbOption3)
            cbOption4.text = handleVisibityForNull(result.get(position).opt1, cbOption4)
        } else if (Math.random() % 5 == 0.0 || Math.random() % 7 == 0.0 || Math.random() % 9 == 0.0) {
            cbOption1.text = handleVisibityForNull(result.get(position).opt1, cbOption1)
            cbOption2.text = handleVisibityForNull(result.get(position).opt2, cbOption2)
            cbOption3.text = handleVisibityForNull(result.get(position).opt3, cbOption3)
            cbOption4.text = handleVisibityForNull(result.get(position).opt4, cbOption4)
        } else if (Math.random() % 3 >= 5.0) {
            cbOption1.text = handleVisibityForNull(result.get(position).opt4, cbOption1)
            cbOption2.text = handleVisibityForNull(result.get(position).opt3, cbOption2)
            cbOption3.text = handleVisibityForNull(result.get(position).opt1, cbOption3)
            cbOption4.text = handleVisibityForNull(result.get(position).opt2, cbOption4)
        } else {
            if (position % 3 == 0 || position % 4 == 0) {
                cbOption1.text = handleVisibityForNull(result.get(position).opt2, cbOption1)
                cbOption2.text = handleVisibityForNull(result.get(position).opt3, cbOption2)
                cbOption3.text = handleVisibityForNull(result.get(position).opt4, cbOption3)
                cbOption4.text = handleVisibityForNull(result.get(position).opt1, cbOption4)
            } else {
                cbOption1.text = handleVisibityForNull(result.get(position).opt3, cbOption1)
                cbOption2.text = handleVisibityForNull(result.get(position).opt1, cbOption2)
                cbOption3.text = handleVisibityForNull(result.get(position).opt3, cbOption3)
                cbOption4.text = handleVisibityForNull(result.get(position).opt4, cbOption4)
            }

        }

        txtQuestion.text = result.get(position).question

        cbOption1.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                if (isChecked) {
                    cbOption1.isChecked = true
                    cbOption2.isChecked = false
                    cbOption3.isChecked = false
                    cbOption4.isChecked = false
                    onItemOptionClick.OnItemOptionClick(
                        cbOption1.text.toString().trim(),
                        result.get(position).answer
                    )
                } else {
                    onItemOptionClick.OnItemOptionClick(
                        "",
                        result.get(position).answer
                    )
                }
            }

        })


        cbOption2.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                if (isChecked) {
                    cbOption1.isChecked = false
                    cbOption2.isChecked = true
                    cbOption3.isChecked = false
                    cbOption4.isChecked = false
                    onItemOptionClick.OnItemOptionClick(
                        cbOption2.text.toString().trim(),
                        result.get(position).answer
                    )
                } else {
                    onItemOptionClick.OnItemOptionClick(
                        "",
                        result.get(position).answer
                    )
                }
            }

        })
        cbOption3.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                if (isChecked) {
                    cbOption1.isChecked = false
                    cbOption2.isChecked = false
                    cbOption3.isChecked = true
                    cbOption4.isChecked = false
                    onItemOptionClick.OnItemOptionClick(
                        cbOption3.text.toString().trim(),
                        result.get(position).answer
                    )
                } else {
                    onItemOptionClick.OnItemOptionClick(
                        "",
                        result.get(position).answer
                    )
                }
            }

        })

        cbOption4.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                if (isChecked) {
                    cbOption1.isChecked = false
                    cbOption2.isChecked = false
                    cbOption3.isChecked = false
                    cbOption4.isChecked = true
                    onItemOptionClick.OnItemOptionClick(
                        cbOption4.text.toString().trim(),
                        result.get(position).answer
                    )
                } else {
                    onItemOptionClick.OnItemOptionClick(
                        "",
                        result.get(position).answer
                    )
                }
            }

        })

        view.addView(slidingLayout, 0)
        return slidingLayout
    }

    fun handleVisibityForNull(opt: String, cb: CheckBox): String? {
        if (opt.equals("null") || opt.isNullOrEmpty())
            cb.visibility = View.GONE
        else cb.visibility = View.VISIBLE
        return opt
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view.equals(`object`)
    }

    interface OnItemOptionClick {
        fun OnItemOptionClick(selectedAnswer: String, correctAnswer: String)
    }

}