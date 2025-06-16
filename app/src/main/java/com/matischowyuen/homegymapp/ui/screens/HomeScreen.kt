package com.matischowyuen.homegymapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Choose a muscle group",
                        fontSize = 20.sp
                    )
                }
            )
        }
    ) { padding ->
        val muscles = listOf(
            "abs", "adductor", "biceps", "calves", "cardio", "chest",
            "forearms", "glutes", "hamstrings", "lats", "lower back",
            "middle back", "obliques", "shoulders", "traps", "triceps"
        )

        LazyColumn(
            contentPadding = padding,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(muscles) { muscle ->
                Button(
                    onClick = { navController.navigate("list/$muscle") },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF3B5BA0),
                        contentColor = Color.White
                    ),
                    shape = MaterialTheme.shapes.large,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                ) {
                    Text(text = muscle.replaceFirstChar { it.uppercaseChar() })
                }
            }
        }
    }
}
