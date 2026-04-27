package com.asdroid.calculator

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class CalculatorViewModel : ViewModel(){
  var state by mutableStateOf(CalculatorState())
      private set 
      
     fun onAction(action : CalculatorAction){
         when(action){
         is CalculatorAction.Number -> enterNumber(action.digit)
         is CalculatorAction.Operation -> enterOperation(action.symbol)
         is CalculatorAction.Clear -> clear()
         is CalculatorAction.Calculate -> calculate()
         is CalculatorAction.Delete -> delete()
         is CalculatorAction.Decimal -> enterDecimal()
        }
    }
    
    private fun clear(){
        state = CalculatorState()
    }
    
    private fun delete(){
        state = when {
            state.result.isNotEmpty() -> CalculatorState()
            state.secondNumber.isNotEmpty() -> state.copy(secondNumber = state.secondNumber.dropLast(1))
            state.operation.isNotEmpty() -> state.copy(operation = "")
            state.firstNumber.isNotEmpty() -> state.copy(firstNumber = state.firstNumber.dropLast(1))
            else -> CalculatorState()
        }
    }
    
    private fun enterNumber(digit : Int){
        if (state.result.isNotEmpty()) return
        if (state.operation.isEmpty()) {
           state = state.copy(firstNumber = state.firstNumber + digit)
        return 
        }
        state = state.copy(secondNumber = state.secondNumber + digit)
    }
    
    private fun enterOperation(symbol : String){
        when {
            state.firstNumber.isEmpty() -> return 
            state.result.isNotEmpty() -> return 
            else -> state = state.copy(operation = symbol)
        }
    }
    
    private fun calculate(){
        val first = state.firstNumber.toDoubleOrNull() ?: return
        val second = state.secondNumber.toDoubleOrNull() ?: return 
        val result = when (state.operation){
            "+" -> first + second 
            "-" -> first - second 
            "×" -> first * second
            "÷" -> if (second == 0.0) "Error" else "${first / second}"
            else -> return 
        }
        val resultText = if (result.toString().endsWith(".0"))
            result.toString().dropLast(2)
            else 
            result.toString() 
      state = state.copy(result = resultText)
    }
    
    private fun enterDecimal(){
        if (state.operation.isEmpty()) {
            if (state.firstNumber.contains(".")) return
            state = state.copy(firstNumber = state.firstNumber + ".")
            return
        }
        
        if (state.secondNumber.contains(".")) return 
           state = state.copy(secondNumber = state.secondNumber + ".")
    }
}

