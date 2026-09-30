package com.example.sistemaestudiantes.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navController: NavHostController,
    matricula: String,
    nombre: String,
    carrera: String,
    turno: String,
    estatus: String
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle del Estudiante") },
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
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(text = "Información Registrada", style = MaterialTheme.typography.headlineSmall)
                    HorizontalDivider()
                    Text(text = "Matrícula: $matricula", style = MaterialTheme.typography.bodyLarge)
                    Text(text = "Nombre: $nombre", style = MaterialTheme.typography.bodyLarge)
                    Text(text = "Carrera: $carrera", style = MaterialTheme.typography.bodyLarge)
                    Text(text = "Turno: $turno", style = MaterialTheme.typography.bodyLarge)
                    Text(text = "Estatus: $estatus", style = MaterialTheme.typography.bodyLarge)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Volver al Formulario")
            }
        }
    }
}