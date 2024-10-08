package com.example.navigationsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
                    MyNavigationApp()
                }
            }
        }
    }
}

@Composable
fun MyNavigationApp() {

    // This object manages the navigation within the NavHost, It keeps track of the
    // back stack and provides methods to navigate between composable
    val navController = rememberNavController()

    // NavHost:- This is a composable function that displays other composable
    // functions based on the current navigation state. It’s similar
    // to a ‘frame’ that swaps out different content based on where you are in the app.
    NavHost(navController = navController, startDestination = "first_screen") {

        // rout:"firstscreen" is the destination here

        // composable("firstscreen") in a way to declare a destination in the navigation
        // graph and specify what UI should be shown when navigation to that destination
        composable("first_screen") {
            FirstScreen { name, age ->

                // You navigate between composables using the NavController like this

                // We are passing $name to the second screen when we are
                // navigating to it
                navController.navigate("second_screen/$name/$age")
            }
        }

        // Defines a destination name "secondscreen"

        // The second screen is accepting {name} argument
        composable(route = "second_screen/{name}/{age}") {

            // we are going to get the name from the it: NavBackStackEntry

            // The argument can be null, we will get the string by the key
            val name = it.arguments?.getString("name") ?: "no name"

            // ----------------------------------------------------------------01-04-2024
            // I have to learn NavBackStackEntry type
            val age = it.arguments?.getString("age") ?: "18"

            SecondScreen(
                name,
                age.toInt(),
                navigateToFirstScreen = { navController.navigate("first_screen") },
                navigateToThirdScreen = { navController.navigate("third_screen") })
        }

        composable("third_screen") {
            ThirdScreen {
                navController.navigate("first_screen")
            }

        }
    }
}







