package com.example.aerlingusassessment.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.aerlingusassessment.MainActivityViewModel
import com.example.aerlingusassessment.SelectedCalculationMethod

@Composable
fun MainScreen(paddingValues: PaddingValues) {
    val viewModel: MainActivityViewModel = hiltViewModel()
    var firstNumber by remember { mutableStateOf("") }
    var secondNumber by remember { mutableStateOf("") }
    var selectedMethod by remember { mutableStateOf(SelectedCalculationMethod.NONE) }
    val answer by viewModel.answer.collectAsState()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        NumberOnlyTextField(
            modifier = Modifier.wrapContentWidth(),
            text = firstNumber,
            onValueChange = {
                firstNumber = it
                if (it.isNotEmpty() && secondNumber.isNotEmpty()) {
                    val answerFromOperation = selectedMethod.operation(
                        firstNumber.toInt(),
                        secondNumber.toInt()
                    )
                    viewModel.updateAnswer(answerFromOperation)
                }
            }
        )
        NumberOnlyTextField(
            modifier = Modifier.wrapContentWidth(),
            text = secondNumber,
            onValueChange = {
                secondNumber = it
                if (it.isNotEmpty() && firstNumber.isNotEmpty()) {
                    val answerFromOperation = selectedMethod.operation(
                        firstNumber.toInt(),
                        secondNumber.toInt()
                    )
                    viewModel.updateAnswer(answerFromOperation)
                }
            }
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ButtonWithText(buttonText = "+") {
                selectedMethod = SelectedCalculationMethod.ADDITION
                viewModel.performCalculation(selectedMethod, firstNumber, secondNumber)
            }

            ButtonWithText(buttonText = "-") {
                selectedMethod = SelectedCalculationMethod.SUBTRACTION
                viewModel.performCalculation(selectedMethod, firstNumber, secondNumber)

            }

            ButtonWithText(buttonText = "*") {
                selectedMethod = SelectedCalculationMethod.MULTIPLICATION
                viewModel.performCalculation(selectedMethod, firstNumber, secondNumber)
            }

            ButtonWithText(buttonText = "/") {
                selectedMethod = SelectedCalculationMethod.DIVISION
                viewModel.performCalculation(selectedMethod, firstNumber, secondNumber)
            }
        }

        TextField(
            value = if (answer == -1 && selectedMethod == SelectedCalculationMethod.NONE) "No Calculation Method Selected" else answer.toString(),
            onValueChange = {},
            readOnly = true,
            modifier = Modifier.fillMaxWidth()
        )
    }
}