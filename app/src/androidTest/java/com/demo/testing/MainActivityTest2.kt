package com.demo.testing

import android.content.ComponentName
import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import androidx.test.InstrumentationRegistry.getInstrumentation
import androidx.test.InstrumentationRegistry.getTargetContext
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.intent.matcher.IntentMatchers.*
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withChild
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.runner.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.rule.ActivityTestRule
import com.demo.testing.coffee.Coffee
import com.demo.testing.coffee.CoffeeActivity
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.not
import org.hamcrest.core.AllOf.allOf
import org.junit.After
import org.junit.Rule
import org.junit.Before

@RunWith(AndroidJUnit4::class)
class MainActivityTest2 {

 @Rule
  @JvmField
  var activityActivityTestRule = ActivityTestRule(MainActivity::class.java)

  var testContext = getInstrumentation().getContext()
  var testRes = testContext.getResources()

// @Rule @JvmField
//  var intentTestRule: IntentsTestRule<MainActivity> = IntentsTestRule(MainActivity::class.java,false,true)

  @Before
  @Throws(Exception::class)
  fun setUp() {
    Intents.init()

  }

  @Test
  fun test_floating_action_button_display() {
    onView(withId(R.id.fab)).check(matches(isDisplayed()))
  }

  @Test
  fun testCollapseToolbarImageViewHasContentDesc() {
    onView(withId(R.id.collapseImage))
      .check(matches(withContentDescription(R.string.image_content_desc)))
  }

  @Test
  fun testToolbarhasCorrectTitleText(){
    onView(allOf(withParent(isAssignableFrom(Toolbar::class.java)), isAssignableFrom(TextView::class.java)))
      .check(matches(withText(R.string.toolbarTitle)))
  }

  @Test /*Check corret coffee data at correct position in recyclerView */
  fun testCoffeeRecyclerViewHasHasCoffeeData(){
    var coffeeData = Coffee(testRes.getDrawable(R.drawable.cof),"Coffee 1")

    onView(withId(R.id.coffee_list)).check(matches(hasCoffeeDataForPosition(0,coffeeData)))
  }

  @Test
  fun testIntentedActivityOnItemClickRecyclerView(){
//    onView(withText("Coffee 7")).check(matches(not(isDisplayed())))
    onView(withId(R.id.coffee_list)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(2))
//    onView(withText("Coffee 7")).check(matches(isDisplayed()))
    onView(withId(R.id.coffee_list)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2,click()))
    intended(hasComponent(CoffeeActivity::class.java.name))
    pressBack()
//    onView(withText("Coffee 7")).check(matches(isDisplayed()))
  }

  fun hasCoffeeDataForPosition(pos:Int, data:Coffee): Matcher<View>{
    return object :BoundedMatcher<View,RecyclerView>(RecyclerView::class.java){
      override fun describeTo(description: Description?) {
        description?.appendText("Item doesnot has coffee data at position $pos")
      }

      override fun matchesSafely(recyclerView: RecyclerView?): Boolean {
        var viewHolder:RecyclerView.ViewHolder? = recyclerView?.findViewHolderForAdapterPosition(pos)
        return withChild(withText(data.name)).matches(viewHolder?.itemView)
      }
    }
  }

  @After
  @Throws(Exception::class)
  fun tearDown() {
    Intents.release()
  }
}




/*
//Match(MATCHER)onView(withId/withText/allOf)
// Act(ACTION)
// Check(ASSERTION)(check(matches))
Action
TypeTextAction ScrollToAction KeyEventAction EditorAction
AdapterDataLoaderAction GeneralSwipeAction GeneralClickAction
CloseKeyboardAction OpenLinkAction closeKeyboardAction
OpenLinkAction ReplaceTextAction
*/


/*  Match

Espresso [Matcher]
ViewMatchers
CursorMatchers
LayoutMatchers
RootMatchers
PreferenceMatchers
BoundedMatchers<T,S extends T>

Enabled focusable Displayed Checked Selected
 */