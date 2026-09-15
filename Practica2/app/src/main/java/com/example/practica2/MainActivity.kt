package com.example.practica2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Calculadora()
        }
    }
}

@Composable
fun Calculadora() {

    var pantalla by remember { mutableStateOf("") }
    var numeroAnterior by remember { mutableStateOf(0.0) }
    var operacion by remember { mutableStateOf("") }
    var nuevoNumero by remember { mutableStateOf(true) }

    fun presionarNumero(numero: String) {

        if (nuevoNumero) {
            pantalla = numero
            nuevoNumero = false
        } else {
            pantalla += numero
        }
    }

    fun presionarOperacion(op: String) {

        val numero = pantalla.toDoubleOrNull()

        if (numero != null) {
            numeroAnterior = numero
            operacion = op
            nuevoNumero = true
        }
    }

    fun calcular() {

        val numeroActual = pantalla.toDoubleOrNull()

        if (numeroActual == null) {
            return
        }

        val resultado = when (operacion) {

            "+" -> numeroAnterior + numeroActual

            "-" -> numeroAnterior - numeroActual

            "*" -> numeroAnterior * numeroActual

            "/" -> {
                if (numeroActual != 0.0) {
                    numeroAnterior / numeroActual
                } else {
                    null
                }
            }

            else -> numeroActual
        }

        if (resultado != null) {
            pantalla = resultado.toString()
            numeroAnterior = resultado
            nuevoNumero = true
        } else {
            pantalla = "Error"
            nuevoNumero = true
        }
    }

    fun limpiar() {
        pantalla = ""
        numeroAnterior = 0.0
        operacion = ""
        nuevoNumero = true
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = if (pantalla.isEmpty()) "0" else pantalla,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {

            Button(
                onClick = { presionarNumero("7") },
                modifier = Modifier.weight(1f)
            ) {
                Text("7")
            }

            Button(
                onClick = { presionarNumero("8") },
                modifier = Modifier.weight(1f)
            ) {
                Text("8")
            }

            Button(
                onClick = { presionarNumero("9") },
                modifier = Modifier.weight(1f)
            ) {
                Text("9")
            }

            Button(
                onClick = { presionarOperacion("/") },
                modifier = Modifier.weight(1f)
            ) {
                Text("/")
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {

            Button(
                onClick = { presionarNumero("4") },
                modifier = Modifier.weight(1f)
            ) {
                Text("4")
            }

            Button(
                onClick = { presionarNumero("5") },
                modifier = Modifier.weight(1f)
            ) {
                Text("5")
            }

            Button(
                onClick = { presionarNumero("6") },
                modifier = Modifier.weight(1f)
            ) {
                Text("6")
            }

            Button(
                onClick = { presionarOperacion("*") },
                modifier = Modifier.weight(1f)
            ) {
                Text("*")
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {

            Button(
                onClick = { presionarNumero("1") },
                modifier = Modifier.weight(1f)
            ) {
                Text("1")
            }

            Button(
                onClick = { presionarNumero("2") },
                modifier = Modifier.weight(1f)
            ) {
                Text("2")
            }

            Button(
                onClick = { presionarNumero("3") },
                modifier = Modifier.weight(1f)
            ) {
                Text("3")
            }

            Button(
                onClick = { presionarOperacion("-") },
                modifier = Modifier.weight(1f)
            ) {
                Text("-")
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {

            Button(
                onClick = { presionarNumero("0") },
                modifier = Modifier.weight(1f)
            ) {
                Text("0")
            }

            Button(
                onClick = { limpiar() },
                modifier = Modifier.weight(1f)
            ) {
                Text("C")
            }

            Button(
                onClick = { calcular() },
                modifier = Modifier.weight(1f)
            ) {
                Text("=")
            }

            Button(
                onClick = { presionarOperacion("+") },
                modifier = Modifier.weight(1f)
            ) {
                Text("+")
            }
        }
    }
}