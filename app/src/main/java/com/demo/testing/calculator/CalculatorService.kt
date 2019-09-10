package com.demo.testing.calculator

interface CalculatorService {
  fun add(num1:Double,num2:Double):Double
  fun sub(num1:Double,num2:Double):Double
  fun multiply(num1:Double,num2:Double):Double
  fun divide(num1:Double,num2:Double):Double
}

class MathApplication(val calculatorService: CalculatorService){
  fun add(num1:Double,num2:Double) = calculatorService.add(num1,num2)
  fun sub(num1:Double,num2:Double) = calculatorService.sub(num1,num2)
  fun multiply(num1:Double,num2:Double) = calculatorService.multiply(num1,num2)
  fun divide(num1:Double,num2:Double) = calculatorService.divide(num1,num2)
}

open class Calculator : CalculatorService{
  override fun add(num1: Double, num2: Double) = num1 + num2

  override fun sub(num1: Double, num2: Double): Double {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun multiply(num1: Double, num2: Double): Double {
    return 0.0
  }

  override fun divide(num1: Double, num2: Double): Double {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}