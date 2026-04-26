package com.asdroid.calculator.ui

import androidx.compose.runtime.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape

@Composable
fun CalculatorButton(
  symbol: String,
  modifier: Modifier = Modifier,
  backgroundColor: Color = Color(0xFF424242),
  onClick : () -> Unit
){
  Button(onClick = onClick,
         modifier = Modier
         .padding(8.dp),
         shape = RoundedCornerShape(50),
         contentPadding = PaddingValues(16.dp),
         colors = ButtonDefaults.buttonColors(
    containerColor = backgroundColor
    ),
  modifier = modifier
  ){
  Text(
    text = symbol,
    fontSize = 24.sp,
    color = Color.White
  )
 }
}
