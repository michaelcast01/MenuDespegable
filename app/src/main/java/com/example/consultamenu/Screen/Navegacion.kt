package com.example.consultamenu.Screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*

data class Usuario(val nombre: String, val correo: String, val profesion: String)

@Composable
fun Navigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val listaUsuarios = remember { mutableStateListOf<Usuario>() }

    NavHost(
        navController = navController,
        startDestination = "screen_a",
        modifier = modifier
    ) {
        composable("screen_a") {
            ScreenA(navController, listaUsuarios)
        }
        composable("screen_b") {
            ScreenB(navController, listaUsuarios)
        }
    }
}
