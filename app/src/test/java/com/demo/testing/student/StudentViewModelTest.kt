package com.demo.testing.student

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.demo.testing.rxjavaschedulers.RxImmediateSchedulerRule
import com.demo.testing.rxjavaschedulers.RxSchedulerOverrides
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class StudentViewModelTest{

  @Mock
  lateinit var studentListInteractor: StudentListInteractor

  lateinit var viewModel: StudentViewModel

  @get:Rule
  val rxOverride = RxImmediateSchedulerRule()

  val student by lazy { Student("","",12) }
  val studentList  by lazy {
    listOf(
      Student("", "", 12),
      Student("", "", 13)
    )
  }

  @Before
  fun setup(){
    viewModel = StudentViewModel(studentListInteractor)

    viewModel.state()

    whenever(studentListInteractor.addExternalStudent(student)).thenReturn(Single.just(19L))
    whenever(studentListInteractor.getStudentList()).thenReturn(Single.just(studentList))
    whenever(studentListInteractor.getStudentName()).thenReturn(Maybe.just("Amit"))
  }

  @Test
  fun addStudent(){
    viewModel.addStudent(student)
    verify(studentListInteractor).addExternalStudent(student)
    verify(studentListInteractor).getStudentList()
  }

  @Test
  fun test_getStudentList(){
    viewModel.getStudentList()
    val source = viewModel.state()
    val testObserver = TestObserver<List<Student>>()
    source.subscribe(testObserver)
    testObserver.assertNoErrors()
    testObserver.assertValue{
      it.equals(studentList)
    }
    verify(studentListInteractor).getStudentList()
  }

  @Test
  fun test_getStudentName(){
    viewModel.getStudentName()
    verify(studentListInteractor).getStudentName()
  }
}