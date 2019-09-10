/*
package com.demo.testing.student

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.EmptyResultSetException
import androidx.room.Room
import com.demo.testing.rxjavaschedulers.RxImmediateSchedulerRule
import io.reactivex.observers.TestObserver
import org.junit.After
import org.junit.Before
import java.lang.Exception
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class StudentDaoTest {

  lateinit var db: StudentDataBase

  lateinit var dbInstance:StudentDao

//  @get:Rule
//  val schedulerOverrides = RxImmediateSchedulerRule()

//  @get:Rule
//  var instantTaskExecutorRule = InstantTaskExecutorRule()

  var context = Mockito.mock(Context::class.java)

  var dbMock = Mockito.mock(SQLiteDatabase::class.java)

  @Before
  @Throws(Exception::class)
  fun setup() {
    db = Room.inMemoryDatabaseBuilder(context, StudentDataBase::class.java)
      .allowMainThreadQueries()
      .build()

    dbInstance = db.listItems
  }

  @Test
  fun getCollegeStudentName_should_return__empty_row_success(){
    val source  = dbInstance.getCollegeStudentName()
    val testObserver = TestObserver<String>()
    source.subscribe(testObserver)
    testObserver.assertNoErrors()
    testObserver.assertComplete()
  }

  @Test
  fun getCollegeStudentName_should_return__empty_row(){
    var source = db.listItems.getCollegeStudentReg()
    val testObserver = TestObserver<Long>()
    source.subscribe(testObserver)
    testObserver.assertError(EmptyResultSetException::class.java)
    testObserver.assertNotComplete()
  }



  @After
  @Throws(Exception::class)
  fun close(){
    db.close()
  }
}*/
