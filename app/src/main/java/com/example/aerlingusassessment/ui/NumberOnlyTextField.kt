package com.example.aerlingusassessment.ui

import android.widget.NumberPicker.OnValueChangeListener
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun NumberOnlyTextField(modifier: Modifier, text:String, onValueChange: (String) -> Unit) {
    TextField(
        value = text,
        onValueChange = onValueChange,
        modifier = modifier,
        placeholder = {
            Text(
                text = "Enter A Number",
                modifier = modifier
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

@Preview
@Composable
fun Preview() {
    NumberOnlyTextField(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight(), ""
    ){}
}