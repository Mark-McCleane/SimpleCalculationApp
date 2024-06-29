package com.example.aerlingusassessment


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainActivityViewModel : ViewModel() {
    private val _answer = MutableStateFlow<Int>(0)
    val answer:StateFlow<Int> = _answer

    fun updateAnswer(answerParam: Int) {
        _answer.value = answerParam
    }
}