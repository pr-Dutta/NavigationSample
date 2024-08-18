package com.example.navigationsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.navigationsample.ui.theme.NavigationSampleTheme

class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationSampleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyNavApp()
                }
            }
        }
    }
}

@Composable
fun MyNavApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Home.rout) {
        composable(Screen.Home.rout) { HomeScreen(navController) }
        composable(Screen.Settings.rout) { SettingsScreen(navController) }
        composable(
            route = Screen.Details.rout,
            // arguments: This specifies the arguments that this route expects.
            // In this case, it's a list containing a single argument named "itemId".

            // navArgument("itemId"): This defines an argument called "itemId"
            // for this route.

            // type = NavType.StringType: This specifies that the "itemId"
            // argument is of type String. Jetpack Compose supports several
            // argument types, such as StringType, IntType, FloatType, etc
            arguments = listOf(navArgument("itemId") { type = NavType.StringType } )
        ) { backStackEntry ->
            // backStackEntry: The lambda parameter backStackEntry
            // represents the entry in the back stack for this composable.
            // It holds information about the state of the navigation,
            // including any arguments passed to this screen.

            val itemId = backStackEntry.arguments?.getString("itemId") ?: "No Id"
            // backStackEntry.arguments: This returns a Bundle containing
            // the arguments passed to this route.

            DetailScreen(navController, itemId)
        }
    }
}