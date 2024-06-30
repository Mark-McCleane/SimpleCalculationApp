package com.example.aerlingusassessment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import com.example.aerlingusassessment.ui.MainScreen
import com.example.aerlingusassessment.ui.theme.AerLingusAssessmentTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AerLingusAssessmentTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Calculation App") },
                            modifier = Modifier
                        )
                    }
                ) { innerPadding ->
                    MainScreen(innerPadding)
                }
            }
        }
    }
}

