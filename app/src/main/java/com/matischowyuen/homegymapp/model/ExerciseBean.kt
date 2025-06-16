package com.matischowyuen.homegymapp.model

data class ExerciseBean(
    val title: String,
    val id: String,
    val video: String,
    val equipment: String,
    val difficulty: String,
    val type: String,
    val breathing_instructions: String,
    val primary: List<String>,
    val secondary: List<String>,
    val instructions: List<String>,
    val tips: List<String>
)
