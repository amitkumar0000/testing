package com.demo.testing.pg

import com.nhaarman.mockito_kotlin.whenever
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Spy
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CalculatorTest {

  @Mock
  lateinit var calculator:Calculator

  @Spy
  var calc = Calculator()


  @Before
  fun setup(){
    whenever(calculator.add(2,3)).thenReturn(5)
  }

  @Test
  fun add() {

    var sum = calculator.add(2,3)

    Mockito.verify(calculator).add(2,3)

    assertEquals(sum,5)

  }


  @Test
  fun spyAdd(){

    Mockito.doReturn(74).whenever(calc).add(34,40)

    var sum = calc.add(34,40)

    assertEquals(sum,74)
  }
}