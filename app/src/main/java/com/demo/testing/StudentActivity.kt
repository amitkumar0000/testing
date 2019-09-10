package com.demo.testing

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import com.demo.testing.student.StudentFeedFragment

import kotlinx.android.synthetic.main.activity_student.*

class StudentActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_student)
    setSupportActionBar(toolbar)

    attachStudentFragment()
  }

  private fun attachStudentFragment() {
    supportFragmentManager.beginTransaction()
      .add(R.id.student_frame,StudentFeedFragment(),"StudentFragment")
      .addToBackStack(null)
      .commit()
  }
}
