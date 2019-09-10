package com.demo.testing.student

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demo.testing.R

class StudentAdapter(): RecyclerView.Adapter<StudentViewHolder>() {

  var studentList = mutableListOf<Student>()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
    return StudentViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.student_row,parent,false))
  }

  override fun getItemCount(): Int {
    return studentList.size
  }

  override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
    holder.nameView.text = studentList[position].name
    holder.collegeView.text = studentList[position].college
    holder.regView.text = studentList[position].regno.toString()
  }
}