package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SaludoApp()
        }
    }
}

@Composable
fun SaludoApp() {

    var nombre by remember { mutableStateOf("") }
    var saludo by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        TextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Escribe tu nombre") }
        )

        Button(
            onClick = {
                saludo = "¡Hola, $nombre!"
            },
            modifier = Modifier.padding(10.dp)
        ) {
            Text("Saludar")
        }

        Text(text = saludo)
    }
}