package com.example.consultamenu.investigacion

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextoEstilizadoUI(modifier: Modifier = Modifier) {
    val colores = listOf("Negro", "Rojo", "Azul", "Verde", "Naranja")
    val coloresMap = mapOf(
        "Negro" to Color.Black,
        "Rojo" to Color.Red,
        "Azul" to Color.Blue,
        "Verde" to Color.Green,
        "Naranja" to Color(0xFFFF9800)
    )

    val tipografias = listOf("Normal", "Negrita", "Cursiva", "Ligera", "Negrita Cursiva")

    var texto by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }
    var colorSeleccionado by remember { mutableStateOf(colores.first()) }
    var tipografiaSeleccionada by remember { mutableStateOf(tipografias.first()) }

    var expandedColor by remember { mutableStateOf(false) }
    var expandedTipo by remember { mutableStateOf(false) }

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            OutlinedTextField(
                value = texto,
                onValueChange = { texto = it },
                label = { Text("Escribe tu texto") },
                modifier = Modifier.fillMaxWidth(0.85f)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Dropdown de Color
            ExposedDropdownMenuBox(
                expanded = expandedColor,
                onExpandedChange = { expandedColor = !expandedColor }
            ) {
                OutlinedTextField(
                    value = colorSeleccionado,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Color") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedColor)
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = expandedColor,
                    onDismissRequest = { expandedColor = false }
                ) {
                    colores.forEach { color ->
                        DropdownMenuItem(
                            onClick = {
                                colorSeleccionado = color
                                expandedColor = false
                            },
                            text = { Text(color) }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Dropdown de Tipografía
            ExposedDropdownMenuBox(
                expanded = expandedTipo,
                onExpandedChange = { expandedTipo = !expandedTipo }
            ) {
                OutlinedTextField(
                    value = tipografiaSeleccionada,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Tipografía") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedTipo)
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = expandedTipo,
                    onDismissRequest = { expandedTipo = false }
                ) {
                    tipografias.forEach { tipo ->
                        DropdownMenuItem(
                            onClick = {
                                tipografiaSeleccionada = tipo
                                expandedTipo = false
                            },
                            text = { Text(tipo) }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { resultado = texto }) {
                Text("Aplicar estilo")
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = resultado,
                fontSize = 20.sp,
                fontWeight = when (tipografiaSeleccionada) {
                    "Negrita", "Negrita Cursiva" -> FontWeight.Bold
                    "Ligera" -> FontWeight.Light
                    else -> FontWeight.Normal
                },
                fontStyle = when (tipografiaSeleccionada) {
                    "Cursiva", "Negrita Cursiva" -> FontStyle.Italic
                    else -> FontStyle.Normal
                },
                color = coloresMap[colorSeleccionado] ?: Color.Black
            )
        }
    }
}
