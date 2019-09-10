package com.demo.testing.student

import io.reactivex.Maybe
import io.reactivex.Single

open class StudentListInteractor(private val repository: StudentRepository){


  open fun addExternalStudent(student:Student): Single<Long> {
    return repository.addStudent(student)
  }

  open fun getStudentList(): Single<List<Student>> {
    return repository.getStudentList()
  }

  open fun getStudentName():Maybe<String>{
    return repository.getStudentName()
  }

}