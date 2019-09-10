package com.demo.testing.student

import io.reactivex.Maybe
import io.reactivex.Single

open class StudentRepository(private val dataBase: StudentDao) : StudentDBRepository {

  override fun getStudentList(): Single<List<Student>> {
    return dataBase.getAll()
  }

  override fun addStudent(student: Student): Single<Long> {
    return Single.fromCallable{
      dataBase.insertOrUpdate(student)
    }
  }

  override fun getStudentName(): Maybe<String> {
    return dataBase.getCollegeStudentName()
  }
}