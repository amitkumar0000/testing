package com.demo.testing.student

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Maybe
import io.reactivex.Single
import org.junit.Test
import org.junit.Before
import org.mockito.Mockito

class StudentListInteractorTest {

  lateinit var repository: StudentRepository

  lateinit var studentListInteractor: StudentListInteractor

  val student by lazy { Student("Amit","KIIT",825316) }

  val studentlist by lazy {
    listOf(
      Student("Amit","KIIT",825316) ,
      Student("Amit","KIIT",825317)
    )
  }

  @Before
  fun setup() {
    repository = Mockito.mock(StudentRepository::class.java)
    studentListInteractor = StudentListInteractor(repository)
    whenever(repository.addStudent(student)).thenReturn(Single.just(10))
    whenever(repository.getStudentList()).thenReturn(Single.just(studentlist))
    whenever(repository.getStudentName()).thenReturn(Maybe.just("124"))
  }

  @Test
  fun test_ddExternalStudent() {
    studentListInteractor.addExternalStudent(student)
    verify(repository).addStudent(student)

  }

  @Test
  fun test_getStudentList() {
    studentListInteractor.getStudentList()
    verify(repository).getStudentList()
  }

  @Test
  fun test_getStudentName() {
    studentListInteractor.getStudentName()
    verify(repository).getStudentName()
  }
}