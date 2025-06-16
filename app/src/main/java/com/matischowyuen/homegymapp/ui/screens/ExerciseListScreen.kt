package com.matischowyuen.homegymapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import com.matischowyuen.homegymapp.viewmodel.ExerciseViewModel
import androidx.navigation.NavController
import com.matischowyuen.homegymapp.ui.components.ExerciseCard

@Composable
fun ExerciseListScreen(
    navController: NavController,
    muscle: String,
    viewModel: ExerciseViewModel
) {
    val exercises by viewModel.exercises.collectAsState()
    var searchQuery by remember { mutableStateOf("") }

    LaunchedEffect(muscle) {
        viewModel.loadExercises(muscle)
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    ) {
        Text(
            text = "Exercises : ${muscle.replaceFirstChar { it.uppercaseChar() }}",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Research an exercise") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        val filtered = exercises.filter {
            it.title.contains(searchQuery, ignoreCase = true)
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(filtered.indices.toList()) { index ->
                ExerciseCard(
                    exercise = filtered[index],
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = {
                        val realIndex = exercises.indexOf(filtered[index])
                        navController.navigate("detail/$realIndex")
                    }
                )
            }
        }
    }
}
