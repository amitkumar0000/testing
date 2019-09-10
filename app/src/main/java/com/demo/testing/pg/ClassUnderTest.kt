package com.demo.testing.pg

import android.content.Context
import com.demo.testing.R


class ClassUnderTest(val context: Context) {

  fun getHelloWorldString():String{
    return context.getString(R.string.hello_world)
  }
}