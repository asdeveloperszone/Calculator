#  ASDROID Calculator App
## Complete Project Documentation

---

```

           ASDROID DEVELOPMENT             
         Calculator App -- Session 1        
         Date: April 26, 2026              
         Time: 14:41 PKT (Pakistan)        
         Student: ICS 2nd Year             

```

---

##  Project Overview

| Detail | Info |
|---|---|
| **App Name** | Simple Calculator |
| **Package** | com.asdroid.calculator |
| **Developer** | ASDROID |
| **Language** | Kotlin |
| **UI Framework** | Jetpack Compose |
| **Architecture** | MVVM |
| **Min SDK** | 28 (Android 9 Pie) |
| **Target SDK** | 36 (Android 16) |
| **Compile SDK** | 36 |
| **Java Version** | 17 |
| **Gradle Version** | 8.2 |
| **Compose BOM** | 2024.02.00 |
| **Build System** | GitHub Actions |
| **Session Date** | April 26, 2026 |

---

##  Complete Project Structure

```
YourRepo/                              <- GitHub Repo Root
|
+-- .github/                           <- AT ROOT (required by GitHub)
|   +-- workflows/
|       +-- build.yml                  <- Auto builds APK
|
+-- SimpleCalculator/                  <- All project files here
    |
    +-- app/
    |   +-- src/main/
    |   |   +-- java/com/asdroid/calculator/
    |   |   |   +-- CalculatorState.kt      DONE
    |   |   |   +-- CalculatorAction.kt     DONE
    |   |   |   +-- CalculatorViewModel.kt  DONE
    |   |   |   +-- MainActivity.kt         MODIFY LATER
    |   |   |   +-- ui/
    |   |   |       +-- CalculatorButton.kt DONE
    |   |   |       +-- CalculatorScreen.kt IN PROGRESS
    |   |   |       +-- theme/
    |   |   |           +-- Theme.kt        DONE
    |   |   |           +-- Color.kt        DONE
    |   |   |           +-- Type.kt         DONE
    |   |   +-- res/
    |   |   |   +-- values/
    |   |   |   |   +-- strings.xml         DONE
    |   |   |   |   +-- themes.xml          DONE
    |   |   |   |   +-- colors.xml          DONE
    |   |   |   +-- drawable/
    |   |   |   |   +-- ic_launcher_foreground.xml DONE
    |   |   |   +-- mipmap-*/
    |   |   |       +-- ic_launcher.png     DONE
    |   |   +-- AndroidManifest.xml         DONE
    |   +-- build.gradle                    DONE
    |   +-- proguard-rules.pro              DONE
    +-- build.gradle                        DONE
    +-- settings.gradle                     DONE
    +-- gradle.properties                   DONE
    +-- gradle/wrapper/
    |   +-- gradle-wrapper.jar              DONE
    |   +-- gradle-wrapper.properties       DONE
    +-- gradlew                             DONE
    +-- .gitignore                          DONE
```

---

##  Overall Progress

```

  CalculatorState.kt         100% 
  CalculatorAction.kt        100% 
  CalculatorViewModel.kt     100% 
  CalculatorButton.kt        100% 
  CalculatorScreen.kt          0% 
  MainActivity.kt             50% 
                                        
  OVERALL                     70% 

```

---

##  Files Completed -- Code Reference

---

###  CalculatorState.kt
**Purpose:** Stores what to display on screen

```kotlin
package com.asdroid.calculator

data class CalculatorState(
    val firstNumber: String = "",
    val operation: String = "",
    val secondNumber: String = "",
    val result: String = ""
)
```

---

###  CalculatorAction.kt
**Purpose:** Defines all possible button actions

```kotlin
package com.asdroid.calculator

sealed class CalculatorAction {
    data class Number(val digit: Int) : CalculatorAction()
    data class Operation(val symbol: String) : CalculatorAction()
    object Calculate : CalculatorAction()
    object Clear : CalculatorAction()
    object Delete : CalculatorAction()
    object Decimal : CalculatorAction()
}
```

---

###  CalculatorViewModel.kt
**Purpose:** Brain of the calculator -- all logic lives here

```kotlin
package com.asdroid.calculator

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class CalculatorViewModel : ViewModel() {
    var state by mutableStateOf(CalculatorState())
        private set

    fun onAction(action: CalculatorAction) {
        when(action) {
            is CalculatorAction.Number -> enterNumber(action.digit)
            is CalculatorAction.Operation -> enterOperation(action.symbol)
            is CalculatorAction.Calculate -> calculate()
            is CalculatorAction.Clear -> clear()
            is CalculatorAction.Delete -> delete()
            is CalculatorAction.Decimal -> enterDecimal()
        }
    }

    private fun clear() {
        state = CalculatorState()
    }

    private fun delete() {
        state = when {
            state.result.isNotEmpty() -> CalculatorState()
            state.secondNumber.isNotEmpty() -> state.copy(secondNumber = state.secondNumber.dropLast(1))
            state.operation.isNotEmpty() -> state.copy(operation = "")
            state.firstNumber.isNotEmpty() -> state.copy(firstNumber = state.firstNumber.dropLast(1))
            else -> CalculatorState()
        }
    }

    private fun enterNumber(digit: Int) {
        if (state.result.isNotEmpty()) return
        if (state.operation.isEmpty()) {
            state = state.copy(firstNumber = state.firstNumber + digit)
            return
        }
        state = state.copy(secondNumber = state.secondNumber + digit)
    }

    private fun enterOperation(symbol: String) {
        when {
            state.firstNumber.isEmpty() -> return
            state.result.isNotEmpty() -> return
            else -> state = state.copy(operation = symbol)
        }
    }

    private fun calculate() {
        val first = state.firstNumber.toDoubleOrNull() ?: return
        val second = state.secondNumber.toDoubleOrNull() ?: return
        val result = when(state.operation) {
            "+" -> first + second
            "-" -> first - second
            "×" -> first * second
            "÷" -> if (second == 0.0) "Error" else "${first / second}"
            else -> return
        }
        state = state.copy(result = result.toString())
    }

    private fun enterDecimal() {
        if (state.operation.isEmpty()) {
            if (state.firstNumber.contains(".")) return
            state = state.copy(firstNumber = state.firstNumber + ".")
            return
        }
        if (state.secondNumber.contains(".")) return
        state = state.copy(secondNumber = state.secondNumber + ".")
    }
}
```

---

###  CalculatorButton.kt
**Purpose:** Reusable button component for all 19 buttons

```kotlin
package com.asdroid.calculator.ui

import androidx.compose.runtime.Composable
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier

@Composable
fun CalculatorButton(
    symbol: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier
    ) {
        Text(text = symbol)
    }
}
```

---

##  Kotlin Concepts Learned

| Concept | Explanation | Example |
|---|---|---|
| `package` | File organization by folder | `package com.asdroid.calculator` |
| `data class` | Class that stores data only | `data class CalculatorState(...)` |
| `class` | Class with functions & logic | `class CalculatorViewModel` |
| `sealed class` | Fixed set of types | `sealed class CalculatorAction` |
| `object` | Single instance, no data | `object Clear : CalculatorAction()` |
| `val` | Cannot be reassigned | `val name = "ASDROID"` |
| `var` | Can be reassigned | `var state = CalculatorState()` |
| `fun` | Function declaration | `fun onAction(...)` |
| `private fun` | Only class can call it | `private fun clear()` |
| `return` | Exit function early | `if (x) return` |
| `when` | Modern if/else switch | `when(action) { ... }` |
| `is` | Type check in when | `is CalculatorAction.Number` |
| `?:` | Elvis operator (if null) | `toDoubleOrNull() ?: return` |
| `copy()` | Copy object with changes | `state.copy(result = "8")` |
| `dropLast(1)` | Remove last character | `"123".dropLast(1) = "12"` |
| `contains()` | Check if string has value | `"12.5".contains(".")` |
| `isEmpty()` | Check if string is empty | `state.operation.isEmpty()` |
| `isNotEmpty()` | Check if string has value | `state.result.isNotEmpty()` |

---

##  Architecture -- MVVM Pattern

```

              UI LAYER               
   CalculatorScreen.kt               
   CalculatorButton.kt               
   (What user SEES)                  

                onAction()
               

           VIEWMODEL LAYER           
   CalculatorViewModel.kt            
   (BRAIN -- processes actions)       

                state updates
               

             DATA LAYER              
   CalculatorState.kt                
   CalculatorAction.kt               
   (What is STORED)                  

```

---

##  Tech Stack

```

  Language      ->  Kotlin            
  UI            ->  Jetpack Compose   
  Architecture  ->  MVVM              
  Build         ->  Gradle 8.2        
  CI/CD         ->  GitHub Actions    
  Java          ->  Version 17        
  Editor        ->  SPCK Editor       
  Device        ->  Android Phone     

```

---

##  GitHub Actions Workflow

```
Push code to GitHub
        v
GitHub Actions triggers
        v
Ubuntu runner starts
        v
Java 17 setup
        v
Gradle wrapper downloaded
        v
./gradlew assembleDebug
        v
APK built successfully 
        v
APK uploaded as artifact
        v
Download from Actions tab 
```

---

##  App Flow -- How Calculator Works

```
User taps "5"
-> CalculatorAction.Number(5)
-> onAction() called
-> enterNumber(5)
-> state.firstNumber = "5"
-> UI recomposes -> shows "5"

User taps "+"
-> CalculatorAction.Operation("+")
-> onAction() called
-> enterOperation("+")
-> state.operation = "+"
-> UI recomposes -> shows "5+"

User taps "3"
-> CalculatorAction.Number(3)
-> onAction() called
-> enterNumber(3)
-> state.secondNumber = "3"
-> UI recomposes -> shows "3"

User taps "="
-> CalculatorAction.Calculate
-> onAction() called
-> calculate()
-> 5.0 + 3.0 = 8.0
-> state.result = "8.0"
-> UI recomposes -> shows "8.0"
```

---

##  Remaining Work

| File | Task | Status |
|---|---|---|
| `CalculatorScreen.kt` | Build full calculator UI with Compose |  Next Session |
| `MainActivity.kt` | Connect ViewModel to UI |  Next Session |
| `build.yml` | Already working  |  Done |

---

##  Key Decisions Made

| Decision | Reason |
|---|---|
| Jetpack Compose over XML | Modern, less code, better |
| MVVM Architecture | Separation of concerns |
| `delete()` clears on result | Better UX -- result is final |
| Java 17 | Official recommendation for Compose |
| GitHub Actions | Build APK without PC |
| minSdk 28 | Android 9+ support |

---

##  Session Info

```

  Session       : 1                    
  Date          : April 26, 2026       
  Time          : 14:41 PKT            
  Files Created : 4 complete           
  Files Pending : 2                    
  Overall       : 70% complete         
  Next Session  : CalculatorScreen.kt  

```

---

```
© 2026 ASDROID Development
Simple Calculator -- Learning Project
Built with  on Android Phone
```


