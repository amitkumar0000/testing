package com.demo.testing.student

import androidx.room.EmptyResultSetException
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import io.reactivex.observers.TestObserver
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import java.lang.Exception
import org.junit.Test

@RunWith(AndroidJUnit4::class)
class StudentDatabaseTest {

  lateinit var db: StudentDataBase

  @Before
  @Throws(Exception::class)
  fun setup() {
    db = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(), StudentDataBase::class.java)
      .build()
  }

  @Test
  fun getCollegeStudentName_should_return_empty_row_sucess(){
    val source = db.listItems.getCollegeStudentName()
    val testObserver = TestObserver<String>()
    source.subscribe(testObserver)
    testObserver.assertNoErrors()
    testObserver.assertComplete()
  }

  @Test
  fun getCollegeStudentReg_should_return_empty_row_error(){
    val source = db.listItems.getCollegeStudentReg()
    val testObserver = TestObserver<Long>()
    source.subscribe(testObserver)
    testObserver.assertError(EmptyResultSetException::class.java)
    testObserver.assertNotComplete()
  }
  
  @Test
  fun test_insert_success(){
    val studentList = listOf(
      Student("Amit" ,"KIIT", 12345),
      Student("Nikhil", "KIIT",12346),
      Student("Raku", "KIIT",12347)
    )
    db.listItems.insertOrUpdate(studentList)
    val source = db.listItems.getAll()
    val testObserver = TestObserver<List<Student>>()
    source.subscribe(testObserver)
    testObserver.assertValue{
      it.equals(studentList)
    }
    testObserver.assertNoErrors()
    testObserver.assertComplete()
  }



  @After
  @Throws(Exception::class)
  fun close(){
    db.close()
  }
}