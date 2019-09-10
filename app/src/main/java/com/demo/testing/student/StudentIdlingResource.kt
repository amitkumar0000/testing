package com.demo.testing.student

import androidx.test.espresso.IdlingResource

class StudentIdlingResource : IdlingResource {

  var isIdle = false

  var resourceCallback: IdlingResource.ResourceCallback?=null

  override fun getName(): String {
    return StudentIdlingResource::class.java.name
  }

  override fun isIdleNow(): Boolean {
    return isIdle
  }

  override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
    this.resourceCallback = callback
  }

  fun onStudentListComplete(){
    isIdle = true
    notifyResourceCallback()
  }

    fun notifyResourceCallback(){
    if(isIdle){
      resourceCallback?.onTransitionToIdle()
    }
  }
}