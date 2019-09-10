package com.demo.testing.student

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.student_row.view.collegeTextView
import kotlinx.android.synthetic.main.student_row.view.nameTextView
import kotlinx.android.synthetic.main.student_row.view.regTextView

class StudentViewHolder(view:View) : RecyclerView.ViewHolder(view) {
  val nameView = view.nameTextView
  val collegeView = view.collegeTextView
  val regView = view.regTextView
}