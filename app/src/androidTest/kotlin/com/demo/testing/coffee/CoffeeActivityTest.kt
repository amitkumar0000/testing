package com.demo.testing.coffee

import android.app.Instrumentation
import android.content.Intent
import androidx.test.InstrumentationRegistry
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.LayoutAssertions.noEllipsizedText
import androidx.test.espresso.assertion.LayoutAssertions.noOverlaps
import androidx.test.espresso.assertion.PositionAssertions.isCompletelyAbove
import androidx.test.espresso.assertion.PositionAssertions.isCompletelyBelow
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.runner.AndroidJUnit4
import com.google.android.material.snackbar.Snackbar
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers.hasErrorText
import androidx.test.rule.ActivityTestRule
import com.demo.testing.R
import org.hamcrest.CoreMatchers.allOf
import org.junit.After

@RunWith(AndroidJUnit4::class)
class CoffeeActivityTest{


  val context = InstrumentationRegistry.getTargetContext()



  @get:Rule
  val activityTestRule:ActivityTestRule<CoffeeActivity> = object: ActivityTestRule<CoffeeActivity>(CoffeeActivity::class.java){
    override fun getActivityIntent(): Intent {
      val resultIntent = Intent(context,CoffeeActivity::class.java)
      resultIntent.putExtra("Desc Text","Coffee Test Desc Text")
      return resultIntent
    }
  }

  @Before
  @Throws(Exception::class)
  fun setUp() {
    Intents.init()

  }

  @After
  @Throws(Exception::class)
  fun tearDown() {
    Intents.release()
  }

  @Test
  fun test_titleTextViewIsVisible(){
    onView(withId(R.id.titleText)).check(matches(isDisplayed()))
  }

  @Test
  fun test_descTestViewIsVisbile(){
    onView(withId(R.id.descText)).check(matches(isDisplayed()))

  }

  @Test
  fun test_descTestViewDisplayCorrectDesc(){
    onView(withId(R.id.descText)).check(matches(withText("Coffee Test Desc Text")))
  }

  @Test
  fun test_imageViewIsVIsible(){
    onView(withId(R.id.imageView)).check(matches(isDisplayed()))
  }

  @Test
  fun test_fabViewIsVIsible(){
    onView(withId(R.id.fab)).check(matches(isDisplayed()))
  }

  @Test
  fun test_toolbarViewIsVIsible(){
    onView(withId(R.id.toolbar)).check(matches(isDisplayed()))
  }

  /*  Check View correct position */

  @Test
  fun test_titleViewAboveImageView(){
    onView(withId(R.id.titleText)).check(isCompletelyAbove(withId(R.id.imageView)))
  }

  @Test
  fun test_descTextViewBelowImageView(){
    onView(withId(R.id.descText)).check(isCompletelyBelow(withId(R.id.imageView)))
  }

  @Test
  fun test_noOverlapView(){
    onView(withId(R.id.activity_coffee)).check(noOverlaps())
  }

  @Test
  fun test_noEllipseText(){
    onView(withId(R.id.descText)).check(noEllipsizedText())
  }

  @Test
  fun test_FabClickShowSnackBar(){
    onView(withId(R.id.fab)).perform(click())
    onView(allOf(withId(R.id.snackbar_text), withText("Replace with your own action")))
      .check(matches(isDisplayed()));

  }

  @Test
  fun testFab1ClickStartCoffeeListActivityAndDisplayCorrectText(){
    onView(withId(R.id.fab1)).perform(click())
    intended(hasComponent(CoffeeListActivity::class.java.name))
    onView(withId(R.id.appCompatTextView)).check(matches(isDisplayed()))
    onView(withId(R.id.appCompatTextView)).check(matches(withText("Coffee List Activity")))


  }


}