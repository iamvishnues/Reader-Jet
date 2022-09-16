package com.jetpack.reader.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jetpack.reader.app.screens.ReaderSplashScreen
import com.jetpack.reader.app.screens.details.ReaderBookDetailsScreen
import com.jetpack.reader.app.screens.home.ReaderHomeScreen
import com.jetpack.reader.app.screens.login.ReaderLoginScreen
import com.jetpack.reader.app.screens.search.ReaderBookSearchScreen
import com.jetpack.reader.app.screens.stats.ReaderStatsScreen
import com.jetpack.reader.app.screens.update.ReaderBookUpdate

@Composable
fun ReaderNavigation() {
    val navController= rememberNavController()
    NavHost(navController = navController, startDestination =ReaderScreens.SplashScreen.name){
        composable(ReaderScreens.SplashScreen.name){
            ReaderSplashScreen(navController)
        }
        composable(ReaderScreens.ReaderHomeScreen.name){
            ReaderHomeScreen(navController)
        }
        composable(ReaderScreens.LoginScreen.name){
            ReaderLoginScreen(navController = navController)
        }
        composable(ReaderScreens.DetailScreen.name){
            ReaderBookDetailsScreen(navController = navController)
        }
        composable(ReaderScreens.SearchScreen.name){
            ReaderBookSearchScreen(navController=navController)
        }
        composable(ReaderScreens.ReaderStatsScreen.name){
            ReaderStatsScreen(navController = navController)
        }
        composable(ReaderScreens.UpdateScreen.name){
            ReaderBookUpdate(navController = navController)
        }
    }
}