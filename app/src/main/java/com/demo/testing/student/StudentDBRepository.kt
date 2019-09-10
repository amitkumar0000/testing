package com.demo.testing.student

import io.reactivex.Maybe
import io.reactivex.Single

interface StudentDBRepository {
  fun getStudentList(): Single<List<Student>>
  fun addStudent(student:Student): Single<Long>
  fun getStudentName():Maybe<String>
}