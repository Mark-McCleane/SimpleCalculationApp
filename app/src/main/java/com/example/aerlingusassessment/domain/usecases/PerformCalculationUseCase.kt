package com.example.aerlingusassessment.domain.usecases

import com.example.aerlingusassessment.SelectedCalculationMethod

class PerformCalculationUseCase {
    operator fun invoke(
        method: SelectedCalculationMethod,
        firstNumber: String,
        secondNumber: String
    ): Int {
            val firstNumberAsInt = firstNumber.toInt()
            val secondNumberAsInt = secondNumber.toInt()
            return method.operation(firstNumberAsInt, secondNumberAsInt)
    }
}