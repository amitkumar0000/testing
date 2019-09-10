package com.demo.testing.pg

import android.content.Context
import com.demo.testing.R
import com.nhaarman.mockito_kotlin.whenever
import junit.framework.Assert.assertEquals
import org.junit.Test

import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ClassUnderTestTest {

  @Mock
  lateinit var context:Context

  lateinit var context1:Context

  @Before
  fun setup(){
    context1 = Mockito.mock(Context::class.java)

    whenever(context.getString(R.string.hello_world)).thenReturn("Mockito")
  }

  @Test
  fun getHelloWorldString() {
    var classUnderTestTest = ClassUnderTest(context)

    assertEquals(classUnderTestTest.getHelloWorldString(),"Mockito")
  }

  @Test
  fun testMockMethod(){
    var list = Mockito.mock(ArrayList::class.java)
    (list as ArrayList<String>).add("23")

    Mockito.verify(list).add("23")
    assertEquals(0,list.size)
  }

  @Test
  fun testSpyMethod(){
    var list = Mockito.spy(ArrayList::class.java)
    (list as ArrayList<String>).add("23")

    Mockito.verify(list).add("23")
    assertEquals(1,list.size)
  }

  @Test
  fun testQuery(){
    var iterator = Mockito.mock(Iterator::class.java)
    whenever(iterator.next()).thenReturn("Hello").thenReturn("world")

    var sum = iterator.next().toString() + " "+ iterator.next()
    assertEquals(sum,"Hello world")

  }


}