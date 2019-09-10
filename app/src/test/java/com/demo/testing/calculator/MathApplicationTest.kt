package com.demo.testing.calculator

import com.nhaarman.mockito_kotlin.atLeast
import com.nhaarman.mockito_kotlin.atLeastOnce
import com.nhaarman.mockito_kotlin.atMost
import com.nhaarman.mockito_kotlin.doThrow
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.timeout
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.inOrder
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner
import java.lang.RuntimeException


@RunWith(MockitoJUnitRunner::class)
class MathApplicationTest {

  var calculatorService: CalculatorService = mock()

  lateinit var mathApplication: MathApplication

  lateinit var spyMathApplication: MathApplication

  @Spy
  lateinit var calculator: Calculator

  @Before
  fun setup() {
    mathApplication = MathApplication(calculatorService)
    spyMathApplication = MathApplication(calculator)

    whenever(calculatorService.add(1.0, 2.0)).thenReturn(3.0)
    whenever(calculatorService.divide(10.0, 2.0)).thenReturn(5.0)
    whenever(calculatorService.multiply(1.0, 2.0)).thenReturn(2.0)
    whenever(calculatorService.sub(10.0, 2.0)).thenReturn(8.0)

    whenever(calculatorService.add(1.0,23.0)).thenAnswer{ 24.0 }

    whenever(calculator.multiply(10.0,20.0)).thenReturn(200.0)

    doThrow(RuntimeException("Divide by Zero")).`when`(calculatorService).divide(10.0, 0.0)
  }

  @Test
  fun testSpyAdd(){
    var s = spyMathApplication.add(10.0,20.0)
    Mockito.verify(calculator).add(10.0,20.0)
    assertEquals(30.0,s,0.0)
  }

  @Test
  fun testSpyMul(){
    var m = spyMathApplication.multiply(10.0,20.0)
    Mockito.verify(calculator, timeout(1)).multiply(10.0,20.0)
    assertEquals(200.0,m,0.0)
  }

  @Test
  fun add() {
    var add = mathApplication.add(1.0, 2.0)
    assertEquals(add, 3.0, 0.0)

    Mockito.verify(calculatorService, times(1)).add(1.0, 2.0)
    Mockito.verify(calculatorService, times(0)).sub(1.0, 2.0)
    Mockito.verify(calculatorService, atMost(0)).sub(1.0, 2.0)
    Mockito.verify(calculatorService, atLeast(1)).add(1.0, 2.0)
    Mockito.verify(calculatorService, atLeastOnce()).add(1.0, 2.0)
  }

  @Test
  fun sub() {
    var sub = mathApplication.sub(10.0, 2.0)
    verify(calculatorService).sub(10.0, 2.0)
    assertEquals(sub, 8.0, 0.0)
  }

  @Test
  fun multiply() {
    var sub =  mathApplication.sub(10.0, 2.0)
    var multiply = mathApplication.multiply(1.0, 2.0)
    Mockito.verify(calculatorService).multiply(1.0, 2.0)
    assertEquals(multiply, 2.0, 0.0)
    assertEquals(sub, 8.0, 0.0)

    var inOrder = inOrder(calculatorService)

    inOrder.verify(calculatorService).sub(10.0, 2.0)
    inOrder.verify(calculatorService).multiply(1.0, 2.0)
  }

  @Test
  fun divide() {
    var divide = mathApplication.divide(10.0, 2.0)
    Mockito.verify(calculatorService).divide(10.0, 2.0)
    assertEquals(divide, 5.0, 0.0)
  }

  @Test(expected = RuntimeException::class)
  fun divideByZero() {
    mathApplication.divide(10.0, 0.0)
    Mockito.verify(calculatorService).divide(10.0, 0.0)
  }

  @Test
  fun testAdd(){
    var s = mathApplication.add(1.0,23.0)
    Mockito.verify(calculatorService).add(1.0,23.0)
    Mockito.verify(calculatorService, times(1)).add(1.0,23.0)
    assertEquals(s,24.0,0.0)
  }
}