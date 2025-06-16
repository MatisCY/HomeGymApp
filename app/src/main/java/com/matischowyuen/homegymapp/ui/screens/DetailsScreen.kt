package com.matischowyuen.homegymapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.matischowyuen.homegymapp.model.ExerciseBean
import com.matischowyuen.homegymapp.viewmodel.ExerciseViewModel

@Composable
fun DetailScreen(
    navController: NavController,
    index: Int,
    viewModel: ExerciseViewModel = viewModel()
) {
    val list by viewModel.exercises.collectAsState()
    val exercise = list.getOrNull(index)

    exercise?.let {
        val uriHandler = LocalUriHandler.current

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Text(text = it.title, style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(12.dp))

            Text(text = "🧠 Main muscles: ${it.primary.joinToString(", ")}")
            Text(text = "💪 Difficulty: ${it.difficulty}")
            Text(text = "🔧 Equipment: ${it.equipment}")
            Text(text = "🏷️ Type: ${it.type}")

            Spacer(modifier = Modifier.height(16.dp))

            if (it.video.isNotBlank()) {
                Text(
                    text = "▶️ Watch the video",
                    color = Color.Blue,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .clickable { uriHandler.openUri(it.video) }
                )
            }

            Text("📋 Instructions:", style = MaterialTheme.typography.titleMedium)
            it.instructions.forEach { step ->
                Text(text = "- $step", modifier = Modifier.padding(start = 8.dp, top = 4.dp))
            }

            if (it.tips.isNotEmpty()) {
                Spacer(modifier = Modifier.height(16.dp))
                Text("💡 Advices:", style = MaterialTheme.typography.titleMedium)
                it.tips.forEach { tip ->
                    Text(text = "• $tip", modifier = Modifier.padding(start = 8.dp, top = 4.dp))
                }
            }
        }
    } ?: Text("Exercise not found", modifier = Modifier.padding(16.dp))
}

@Preview(showBackground = true)
@Composable
fun PreviewDetailScreen() {
    val fakeExercise = ExerciseBean(
        title = "Concentration Curls",
        id = "concentration_curls",
        video = "https://www.gymstreak.com/animations/Concentration_Curls.webm",
        equipment = "Dumbbell",
        difficulty = "Beginner",
        type = "Isolation",
        breathing_instructions = "",
        primary = listOf("Biceps"),
        secondary = listOf("Forearms"),
        instructions = listOf(
            "Sit on a flat bench with your legs spread apart...",
            "Hold your dumbbell with your weaker arm...",
            "Curl the dumbbell up, moving the forearm only..."
        ),
        tips = listOf(
            "Squeeze the biceps at the top.",
            "Control the movement throughout."
        )
    )

    MaterialTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = fakeExercise.title, style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = "🧠 Main muscles: ${fakeExercise.primary.joinToString(", ")}")
            Text(text = "💪 Difficulty: ${fakeExercise.difficulty}")
            Text(text = "🔧 Equipment: ${fakeExercise.equipment}")
            Text(text = "🏷️ Type: ${fakeExercise.type}")
        }
    }
}

