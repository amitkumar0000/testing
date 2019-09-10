package com.demo.testing.student

import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Maybe
import io.reactivex.Single
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mockito
import java.io.IOException

class StudentRepositoryTest {

  lateinit var dataBase:StudentDao

  lateinit var studentRepository: StudentRepository

  val student by lazy { Student("Amit","KIIT",825316) }

  val studentlist by lazy {
    listOf(
      Student("Amit","KIIT",825316) ,
      Student("Amit","KIIT",825317)
    )
  }

  val exception by lazy { IOException() }

  @Before
  fun setup(){
    dataBase = Mockito.mock(StudentDao::class.java)

    studentRepository = StudentRepository(dataBase)
    whenever(dataBase.getAll()).thenReturn(Single.just(studentlist)).thenReturn(Single.error(exception))
    whenever(dataBase.getCollegeStudentName()).thenReturn(Maybe.just("hello"))
    whenever(dataBase.insertOrUpdate(student)).thenReturn(10)
  }

  @Test
  fun getStudentList() {
    studentRepository.getStudentList()
      .subscribe({
        assert(it.equals(studentlist))
      },{
      })
    studentRepository.getStudentList()
      .subscribe({
        assert(it.equals(studentlist))
      },{
        assertEquals(it, exception)
      })
    verify(dataBase,times(2)).getAll()
  }

  @Test
  fun addStudent() {
    studentRepository.addStudent(student)
      .subscribe({
        assertEquals(it,10)
      },{
        assertEquals(it,exception)
      })
  }

  @Test
  fun getStudentName() {
    studentRepository.getStudentName()
      .subscribe({
        assertEquals("hello",it)
      },{})
    verify(dataBase).getCollegeStudentName()
  }
}