package com.matischowyuen.homegymapp

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.matischowyuen.homegymapp.ui.screens.*
import com.matischowyuen.homegymapp.viewmodel.ExerciseViewModel
import com.matischowyuen.homegymapp.ui.screens.ExerciseListScreen



@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()
    val vm = viewModel<ExerciseViewModel>() // partagé dans tout le graph

    NavHost(navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController)
        }
        composable("list/{muscle}") { backStack ->
            val muscle = backStack.arguments?.getString("muscle") ?: "abs"
            ExerciseListScreen(navController, muscle, vm)
        }
        composable("detail/{index}") { backStack ->
            val index = backStack.arguments?.getString("index")?.toIntOrNull() ?: 0
            DetailScreen(navController, index, vm)
        }
    }
}
