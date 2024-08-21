package io.lemon.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.lemon.android.ui.detail.DetailScreen
import io.lemon.android.ui.home.HomeScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            val navHostController: NavHostController = rememberNavController()
            NavHost(
                modifier = Modifier.fillMaxSize(),
                navController = navHostController,
                startDestination = "home"
            ){
                composable(route = "home") {
                    HomeScreen(navController=navHostController)
                }
                composable(route = "detail") {
                    DetailScreen(navController=navHostController)
                }
            }
        }
    }
}