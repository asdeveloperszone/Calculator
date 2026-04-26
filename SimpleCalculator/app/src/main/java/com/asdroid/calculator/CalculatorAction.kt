package com.asdroid.calculator

sealed class CalculatorAction{
  data class Number(val digit: Int) : CalculatorAction()
  
  data class Operation(val symbol: String) : CalculatorAction()
  object Calculate : CalculatorAction()
  object Clear : CalculatorAction()
  object Delete : CalculatorAction()
  object Decimal : CalculatorAction()
  
  
}