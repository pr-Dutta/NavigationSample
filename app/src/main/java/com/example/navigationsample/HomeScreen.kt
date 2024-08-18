package com.example.navigationsample

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(navController: NavHostController) {

    val itemList = listOf("item1", "item2", "item3")

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for (item in itemList) {
            Button(onClick = {
                navController.currentBackStackEntry?.savedStateHandle?.set("Id", "id-1")
                navController.navigate(Screen.Details.rout)
            }) {
                Text(item)
            }
        }
    }
}