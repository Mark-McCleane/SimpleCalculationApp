package com.example.aerlingusassessment

enum class SelectedCalculationMethod(val symbol: String, val operation: (Int, Int) -> Int) {
    ADDITION("+", { a, b -> a + b }),
    SUBTRACTION("-", { a, b -> a - b }),
    MULTIPLICATION("*", { a, b -> a * b }),
    DIVISION("/", { a, b ->
        if (b != 0) a / b else throw IllegalArgumentException("Division by zero")
    }),
    NONE("none", { a, b -> -1 });

    companion object {
        fun fromSymbol(symbol: String): SelectedCalculationMethod? {
            return entries.find { it.symbol == symbol }
        }
    }
}
