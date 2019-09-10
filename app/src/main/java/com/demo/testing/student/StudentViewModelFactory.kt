package com.demo.testing.student

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import java.lang.IllegalArgumentException

open class StudentViewModelFactory(
  val studentListInteractor: StudentListInteractor
) : ViewModelProvider.Factory {
  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    if (modelClass.isAssignableFrom(StudentViewModel::class.java))
      return StudentViewModel(studentListInteractor) as T
    throw IllegalArgumentException("Unknown Model class")
  }
}