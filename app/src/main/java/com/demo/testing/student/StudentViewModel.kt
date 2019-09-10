package com.demo.testing.student

import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.room.EmptyResultSetException
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

class StudentViewModel(
  val studentListInteractor: StudentListInteractor
) : ViewModel() {

  @VisibleForTesting
  val studentIdlingResource=  StudentIdlingResource()

  val studentState by lazy { BehaviorSubject.create<List<Student>>() }

  fun state() = studentState.hide()

  fun addStudent(student:Student){
     studentListInteractor.addExternalStudent(student)
      .subscribeOn(Schedulers.io())
       .subscribe({getStudentList()},{})
  }

  fun getStudentList(){
     studentListInteractor.getStudentList()
      .subscribeOn(Schedulers.io())
       .subscribe({
         studentState.onNext(it)
         //for testing
         notifyIdlingResource()
       },{
         /*when(it){
           is EmptyResultSetException -> Log.d("TestingDemo "," Empty Row exception")
           else -> Log.d("TestingDemo "," Something went wrong!!")
         }*/
       })
  }

  private fun notifyIdlingResource() {
    studentIdlingResource.onStudentListComplete()
  }

  fun getStudentName(){
    studentListInteractor.getStudentName()
      .subscribeOn(Schedulers.io())
      .subscribe({
//        Log.d("TestingDemo ","Received the student name $it")
      },{
       /* when(it){
          is EmptyResultSetException -> Log.d("TestingDemo "," Empty Row exception")
          else -> Log.d("TestingDemo "," Something went wrong!!")
        }*/
      })
  }

}