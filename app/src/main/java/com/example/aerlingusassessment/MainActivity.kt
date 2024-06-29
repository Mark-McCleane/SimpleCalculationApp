package com.example.aerlingusassessment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aerlingusassessment.ui.ButtonWithText
import com.example.aerlingusassessment.ui.NumberOnlyTextField
import com.example.aerlingusassessment.ui.theme.AerLingusAssessmentTheme
import kotlinx.coroutines.flow.update

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AerLingusAssessmentTheme {
                MainScreen()
            }
        }
    }


    @Composable
    fun MainScreen(viewModel: MainActivityViewModel = viewModel()) {
        var firstNumber by remember { mutableStateOf("") }
        var secondNumber by remember { mutableStateOf("") }
        var selectedMethod by remember {
            mutableStateOf(SelectedCalculationMethod.NONE)
        }
        val answer by viewModel.answer.collectAsState()
        val context = LocalContext.current
        val scope = rememberCoroutineScope()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp),
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
                    performCalculation(selectedMethod, firstNumber, secondNumber, viewModel)
                }

                ButtonWithText(buttonText = "-") {
                    selectedMethod = SelectedCalculationMethod.SUBTRACTION
                    performCalculation(selectedMethod, firstNumber, secondNumber, viewModel)

                }

                ButtonWithText(buttonText = "*") {
                    selectedMethod = SelectedCalculationMethod.MULTIPLICATION
                    performCalculation(selectedMethod, firstNumber, secondNumber, viewModel)
                }

                ButtonWithText(buttonText = "/") {
                    selectedMethod = SelectedCalculationMethod.DIVISION
                    performCalculation(selectedMethod, firstNumber, secondNumber, viewModel)
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

    private fun performCalculation(
        method: SelectedCalculationMethod,
        firstNumber: String,
        secondNumber: String,
        viewModel: MainActivityViewModel
    ) {
        if (firstNumber.isNotEmpty() && secondNumber.isNotEmpty()) {
            val firstNumberAsInt = firstNumber.toInt()
            val secondNumberAsInt = secondNumber.toInt()
            val answerFromOperation = method.operation(firstNumberAsInt, secondNumberAsInt)
            viewModel.updateAnswer(answerFromOperation)
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun MainScreenPreview() {
        AerLingusAssessmentTheme {
            MainScreen()
        }
    }
}

