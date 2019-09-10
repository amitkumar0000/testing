package com.demo.testing.student

import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.demo.testing.R
import com.demo.testing.StudentActivity
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.core.AllOf
import org.junit.After
import org.junit.Test

import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith



@RunWith(AndroidJUnit4::class)
class StudentFeedFragmentTest {

  @get:Rule
  var activityTestRule = ActivityTestRule(StudentActivity::class.java)

  lateinit var studentIdlingResource: StudentIdlingResource

  @Before
  fun init(){
    activityTestRule.activity.supportFragmentManager.beginTransaction()
    studentIdlingResource = (activityTestRule.activity.supportFragmentManager
      .findFragmentByTag("StudentFragment") as StudentFeedFragment).getIdlingResource()
    IdlingRegistry.getInstance().register(studentIdlingResource)
  }

  @After
  fun tearDown(){
    IdlingRegistry.getInstance().unregister(studentIdlingResource)
  }

  @Test
  fun testnameEditTextIsDisplayed(){
    onView(withId(R.id.nameEditText)).check(matches(isDisplayed()))
  }

  @Test
  fun testcollegeEditTextIsDisplayed(){
    onView(withId(R.id.collegeEditText)).check(matches(isDisplayed()))
  }

  @Test
  fun testRegdEditTextIsDisplayed(){
    onView(withId(R.id.regEditText)).check(matches(isDisplayed()))
  }

  fun withRecyclerView(recyclerViewId: Int): RecyclerViewMatcher {
    return RecyclerViewMatcher(recyclerViewId)
  }

  @Test
  fun testdisplayInitialState(){
    onView(withRecyclerView(R.id.student_list).atPosition(0)).check(matches(hasDescendant(withText("Amit"))))
    onView(withRecyclerView(R.id.student_list).atPosition(0)).check(matches(hasDescendant(withText("Kiit"))))
    onView(withRecyclerView(R.id.student_list).atPosition(0)).check(matches(hasDescendant(withText("825316"))))
  }

  @Test
  fun testSubmitClick(){
    onView(withId(R.id.nameEditText)).perform(typeText("Amit"))
    onView(withId(R.id.collegeEditText)).perform(typeText("Kiit"))
    onView(withId(R.id.regEditText)).perform(typeText("825316"))
    onView(withId(R.id.submit)).perform(click())

//    onView(withId(R.id.nameTextView)).check(matches(withText("Amit")))


    onView(withRecyclerView(R.id.student_list).atPosition(1)).check(matches(hasDescendant(withText("Amit"))))
    onView(withRecyclerView(R.id.student_list).atPosition(1)).check(matches(hasDescendant(withText("Kiit"))))
    onView(withRecyclerView(R.id.student_list).atPosition(1)).check(matches(hasDescendant(withText("825316"))))
  }
}