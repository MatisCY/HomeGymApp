package com.matischowyuen.homegymapp.ui.components

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.matischowyuen.homegymapp.model.ExerciseBean

@Preview(showBackground = true)
@Composable
fun PreviewExerciseCard() {
    val sampleExercise = ExerciseBean(
        title = "Push-Up",
        id = "001",
        video = "https://example.com/video.mp4",
        equipment = "None",
        difficulty = "Beginner",
        type = "Strength",
        breathing_instructions = "Inhale on the way down, exhale on the way up.",
        primary = listOf("Chest", "Triceps"),
        secondary = listOf("Shoulders"),
        instructions = listOf("Place hands shoulder-width apart.", "Lower your body.", "Push back up."),
        tips = listOf("Keep your back straight.", "Engage your core.")
    )

    ExerciseCard(
        exercise = sampleExercise,
        modifier = Modifier.padding(16.dp)
    )
}


@Composable
fun ExerciseCard(
    exercise: ExerciseBean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        onClick = onClick
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = exercise.title, fontWeight = FontWeight.Bold)
            Text(text = "Equipment : ${exercise.equipment}")
            Text(text = "Difficulty : ${exercise.difficulty}")
        }
    }
}
