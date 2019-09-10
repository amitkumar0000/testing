package com.demo.testing.student

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demo.testing.MyApp
import com.demo.testing.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_student_feed.collegeEditText
import kotlinx.android.synthetic.main.fragment_student_feed.nameEditText
import kotlinx.android.synthetic.main.fragment_student_feed.regEditText
import kotlinx.android.synthetic.main.fragment_student_feed.submit
import kotlinx.android.synthetic.main.fragment_student_feed.student_list as studentList

/**
 *[StudentFeedFragment] .
 *
 *
 */
open class StudentFeedFragment : Fragment() {

  val adapter: StudentAdapter by lazy { StudentAdapter() }
  val layoutManager: LinearLayoutManager by lazy { LinearLayoutManager(requireContext()) }

  lateinit var viewModel: StudentViewModel
  val disposable by lazy { CompositeDisposable() }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_student_feed, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    viewModel =
      ViewModelProviders.of(this, StudentViewModelFactory(StudentListInteractor(StudentRepository(MyApp.database.listItems)))).get(StudentViewModel::class.java)

    submit.setOnClickListener {
      var name = nameEditText.text.toString()
      var college = collegeEditText.text.toString()
      var regno = regEditText.text.toString()
      if(name.isNullOrEmpty() || college.isNullOrEmpty() || regno.isNullOrEmpty()){
        //Don't do Anything
      }else {
        clearStudentInput()
        viewModel.addStudent(Student(college, name, regno.toLong()))
      }
    }

    disposable.add(
      viewModel.state()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe{refresh(it)}
    )

    setStudentList()

    displayInitialState()

    viewModel.getStudentName()
  }

  fun displayInitialState(){
    viewModel.getStudentList()
  }

  @VisibleForTesting
  fun getIdlingResource():StudentIdlingResource {
    return viewModel.studentIdlingResource
  }

  private fun refresh(it: List<Student>) {

    adapter.studentList = it as MutableList<Student>
    adapter.notifyDataSetChanged()

  }

  private fun clearStudentInput() {
    nameEditText.setText("")
    collegeEditText.setText("")
    regEditText.setText("")
  }

  fun setStudentList() {
    studentList.adapter = adapter
    layoutManager.orientation = RecyclerView.VERTICAL
    studentList.layoutManager = layoutManager
    studentList.addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
  }

  override fun onDestroyView() {
    super.onDestroyView()
    disposable.clear()
  }
}
