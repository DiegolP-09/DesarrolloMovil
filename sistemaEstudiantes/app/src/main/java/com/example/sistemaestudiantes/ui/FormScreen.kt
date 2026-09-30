package com.example.sistemaestudiantes.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.sistemaestudiantes.data.PreferencesManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormScreen(navController: NavHostController) {
    val context = LocalContext.current
    val preferencesManager = remember { PreferencesManager(context) }

    var matricula by remember { mutableStateOf(preferencesManager.getLastMatricula()) }
    var nombre by remember { mutableStateOf(preferencesManager.getLastNombre()) }

    val carreras = listOf("Ingeniería en Software", "Ingeniería Informática", "Licenciatura en Sistemas", "Mecatrónica")
    var expandedDropdown by remember { mutableStateOf(false) }
    var carreraSeleccionada by remember { mutableStateOf(carreras[0]) }

    var turnoSeleccionado by remember { mutableStateOf("Matutino") }
    var esActivo by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Registro de Estudiantes") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = matricula,
                onValueChange = { matricula = it },
                label = { Text("Matrícula") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre Completo") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            // Exposed Dropdown Menu
            ExposedDropdownMenuBox(
                expanded = expandedDropdown,
                onExpandedChange = { expandedDropdown = !expandedDropdown }
            ) {
                OutlinedTextField(
                    value = carreraSeleccionada,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Carrera") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedDropdown) },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = expandedDropdown,
                    onDismissRequest = { expandedDropdown = false }
                ) {
                    carreras.forEach { carrera ->
                        DropdownMenuItem(
                            text = { Text(carrera) },
                            onClick = {
                                carreraSeleccionada = carrera
                                expandedDropdown = false
                            }
                        )
                    }
                }
            }

            // RadioButtons
            Text(text = "Turno:", style = MaterialTheme.typography.titleMedium)
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = (turnoSeleccionado == "Matutino"),
                    onClick = { turnoSeleccionado = "Matutino" }
                )
                Text(text = "Matutino", modifier = Modifier.padding(end = 16.dp))

                RadioButton(
                    selected = (turnoSeleccionado == "Vespertino"),
                    onClick = { turnoSeleccionado = "Vespertino" }
                )
                Text(text = "Vespertino")
            }

            // Switch
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (esActivo) "Estatus: Activo" else "Estatus: Inactivo",
                    style = MaterialTheme.typography.titleMedium
                )
                Switch(
                    checked = esActivo,
                    onCheckedChange = { esActivo = it }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (matricula.isNotBlank() && nombre.isNotBlank()) {
                        preferencesManager.saveLastStudent(matricula, nombre)
                        val estatusStr = if (esActivo) "Activo" else "Inactivo"
                        val route = "detail_screen/$matricula/$nombre/$carreraSeleccionada/$turnoSeleccionado/$estatusStr"
                        navController.navigate(route)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Registrar y Continuar")
            }
        }
    }
}