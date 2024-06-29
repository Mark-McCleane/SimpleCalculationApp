package com.example.aerlingusassessment.ui

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ButtonWithText(buttonText: String, buttonOnClick: () -> Unit) {
    Button(
        onClick = buttonOnClick
    ) {
        Text(text = buttonText)
    }
}

@Composable
@Preview
fun PreviewButtonWithText(){
    ButtonWithText(buttonText = "X") {}
}