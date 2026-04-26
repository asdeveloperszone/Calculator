package com.asdroid.calculator

data class CalculatorState(
  val firstNumber: String = "",
  val operation: String = "",
  val secondNumber: String = "",
  val result: String = ""
)