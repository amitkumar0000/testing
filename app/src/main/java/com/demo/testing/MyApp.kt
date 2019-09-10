package com.demo.testing

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import com.demo.testing.student.StudentDataBase

class MyApp :Application() {

  companion object {
    lateinit var database:StudentDataBase
  }
  override fun onCreate() {
    super.onCreate()

    database = Room.databaseBuilder(this,StudentDataBase::class.java,"student database").build()
  }
}