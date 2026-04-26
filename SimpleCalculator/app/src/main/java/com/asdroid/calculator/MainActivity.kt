package com.asdroid.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.asdroid.calculator.ui.theme.SimpleCalculatorTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.asdroid.calculator.ui.CalculatorScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        window.decorView.setBackgroundColor(android.Graphics.Color.BLACK)
        setContent {
            SimpleCalculatorTheme {
                val viewModel = viewModel<CalculatorViewModel>()
                CalculatorScreen(
                    state = viewModel.state,
                    onAction = viewModel::onAction
                )
            }
        }
    }
}

