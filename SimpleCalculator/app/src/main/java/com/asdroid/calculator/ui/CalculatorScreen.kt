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
    .background(Color.Black)
    .navigationBarsPadding(),
    verticalArrangement = Arrangement.Bottom
  ){
    Text(
      text = if (state.result.isNotEmpty()) state.result
      else "${state.firstNumber}${state.operation}${state.secondNumber}",
      modifier = Modifier
          .fillMaxWidth()
          .padding(top = 24.dp , start = 16.dp , end = 16.dp , bottom = 8.dp),
      fontSize = 36.sp,
      maxLines = 1,
      textAlign = TextAlign.End,
      color = Color.White
    )
    // Inner Column creates here and Rows in it 


    
 }
}
