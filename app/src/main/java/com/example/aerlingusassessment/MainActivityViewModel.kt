package com.example.aerlingusassessment


import androidx.lifecycle.ViewModel
import com.example.aerlingusassessment.domain.usecases.PerformCalculationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val performCalculationUseCase: PerformCalculationUseCase) :
    ViewModel() {
    private val _answer = MutableStateFlow<Int>(0)
    val answer: StateFlow<Int> = _answer

    fun updateAnswer(answerParam: Int) {
        _answer.value = answerParam
    }

    fun performCalculation(
        method: SelectedCalculationMethod,
        firstNumber: String,
        secondNumber: String,
    ) {
        if (firstNumber.isNotEmpty() && secondNumber.isNotEmpty()) {
            val answerFromOperation = performCalculationUseCase(method, firstNumber, secondNumber)
            updateAnswer(answerFromOperation)
        }
    }
}