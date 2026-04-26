package com.asdroid.calculator.ui

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.asdroid.calculator.CalculatorAction
import com.asdroid.calculator.CalculatorState
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background

@Composable 
fun CalculatorScreen(
  state : CalculatorState,
  onAction : (CalculatorAction) -> Unit
){
  Column(
    modifier = Modifier
    .fillMaxSize()
    .padding(8.dp)
    .background(Color.Black)
    .navigationBarsPadding(),
    verticalArrangement = Arrangement.Bottom
  ){
    Text(
      text = if (state.result.isNotEmpty()) state.result
      else "${state.firstNumber}${state.operation}${state.secondNumber}",
      modifier = Modifier
          .fillMaxWidth()
          .padding(16.dp),
      fontSize = 48.sp,
      maxLines = 1,
      textAlign = TextAlign.End
      color = Color.White
    )
    // Row 1
    Row(
      modifier =  Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(8.dp)
    ){
      CalculatorButton(symbol =  "C",backgroundColor = Color(0xFFFF5722), modifier = Modifier.weight(1f)) {
        onAction(CalculatorAction.Clear)
      }
      CalculatorButton(symbol = "⌫",backgroundColor = Color(0xFFFF9800), modifier = Modifier.weight(1f)) {
        onAction(CalculatorAction.Delete)
      }
      CalculatorButton(symbol = "÷", backgroundColor = Color(0xFF2196F3), modifier = Modifier.weight(1f)) {
        onAction(CalculatorAction.Operation("÷"))
      }
      CalculatorButton(symbol = "×", backgroundColor = Color(0xFF2196F3), modifier = Modifier.weight(1f)){
        onAction(CalculatorAction.Operation("×"))
      }
    }
    // Row 2
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(8.dp)
    ){
      CalculatorButton(symbol = "7", modifier = Modifier.weight(1f)) {
        onAction(CalculatorAction.Number(7))
      }
      CalculatorButton(symbol = "8", modifier = Modifier.weight(1f)) {
        onAction(CalculatorAction.Number(8))
      }
      CalculatorButton(symbol = "9", modifier = Modifier.weight(1f)) {
        onAction(CalculatorAction.Number(9))
      }
      CalculatorButton(symbol = "-", backgroundColor = Color(0xFF2196F3), modifier = Modifier.weight(1f)) {
        onAction(CalculatorAction.Operation("-"))
      }
  }
  
  // Row 3
  Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(8.dp)
    ){
      CalculatorButton(symbol = "4", modifier = Modifier.weight(1f)) {
        onAction(CalculatorAction.Number(4))
      }
      CalculatorButton(symbol = "5", modifier = Modifier.weight(1f)) {
        onAction(CalculatorAction.Number(5))
      }
      CalculatorButton(symbol = "6", modifier = Modifier.weight(1f)) {
        onAction(CalculatorAction.Number(6))
      }
      CalculatorButton(symbol = "+", backgroundColor = Color(0xFF2196F3),  modifier = Modifier.weight(1f)) {
        onAction(CalculatorAction.Operation("+"))
      }
  }
  
  // Row 4
  Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(8.dp)
    ){
      CalculatorButton(symbol = "1", modifier = Modifier.weight(1f)) {
        onAction(CalculatorAction.Number(1))
      }
      CalculatorButton(symbol = "2", modifier = Modifier.weight(1f)) {
        onAction(CalculatorAction.Number(2))
      }
      CalculatorButton(symbol = "3", modifier = Modifier.weight(1f)) {
        onAction(CalculatorAction.Number(3))
      }
      CalculatorButton(symbol = "=", backgroundColor = Color(0xFF4CAF50), modifier = Modifier.weight(1f)) {
        onAction(CalculatorAction.Calculate)
      }
  }
  
  // Row 5 
  
  Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(8.dp)
    ){
      CalculatorButton(symbol = "0", modifier = Modifier.weight(2f)) {
        onAction(CalculatorAction.Number(0))
      }
      CalculatorButton(symbol = ".", backgroundColor = Color(0xFF2196F3),  modifier = Modifier.weight(1f)) {
        onAction(CalculatorAction.Decimal)
      }
   }
 }
}
