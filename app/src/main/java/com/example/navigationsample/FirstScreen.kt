package com.example.navigationsample

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FirstScreen(navigateToSecondScreen: (String, Int) -> Unit) {
    val name = remember { mutableStateOf("") }
    val age = remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "This Is The First Screen",
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = name.value,
            onValueChange = { name.value = it },
            label = { Text(text = "Enter Your Name")}
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = age.value.toString(),
            onValueChange = { age.value = it.toInt()},
            label = { Text(text = "Enter Your Age")}
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            navigateToSecondScreen(name.value, age.value)
        }) {
            Text(text = "Go To Second Screen")
        }
    }
}


@Preview
@Composable
fun FirstPreview() {
    //FirstScreen({""})
}