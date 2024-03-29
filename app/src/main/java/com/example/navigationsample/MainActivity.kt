package com.example.navigationsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navigationsample.ui.theme.NavigationSampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationSampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {

    // The object manages the navigation within the NavHost, It keeps track of the
    // back stack and provides methods to navigate between composable
    val navController = rememberNavController()

    // NavHost:- This is a composable function that displays other composable
    // functions based on the current navigation state. It’s similar
    // to a ‘frame’ that swaps out different content based on where you are in the app.
    NavHost(navController = navController, startDestination = "firstscreen") {

        // rout:"firstscreen" is the destination here

        // composable("firstscreen") in a way to declare a destination in the navigation
        // graph and specify what UI should be shown when navigation to that destination
        composable("firstscreen") {
            FirstScreen { name ->

                // You navigate between composables using the NavController like this
                navController.navigate("secondscreen/$name")
            }
        }

        // Defines a destination name "secondscreen"
        composable(route = "secondscreen/{name}") {

            val name = it.arguments?.getString("name") ?: "no name"

            SecondScreen(
                name,
                navigateToFirstScreen = { navController.navigate("firstscreen") },
                navigateToThirdScreen = { navController.navigate("thirdscreen") }
            )
        }

        composable("thirdscreen") {
            ThirdScreen {
                navController.navigate("firstscreen")
            }

            ThirdScreen ({ navController.navigate("firstscreen") })
        }
    }
}







