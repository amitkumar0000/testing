package com.demo.testing.student

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student")
data class Student(
  var college:String,
  var name:String,
  @PrimaryKey var regno:Long
)