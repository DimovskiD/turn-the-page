package com.deluxe1.turnthepage

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.deluxe1.turnthepage.ui.MainScreen
import com.deluxe1.turnthepage.ui.SplashScreen
import com.deluxe1.turnthepage.ui.theme.TurnThePageTheme

class MainActivity : ComponentActivity() {

    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val books = mainViewModel.bookPager.collectAsLazyPagingItems()
            val valid by mainViewModel.valid.collectAsState()
            val navController = rememberNavController()
            val context = LocalContext.current
            TurnThePageTheme {
                NavHost(
                    navController = navController,
                    startDestination = "splash",
                ) {
                    composable(route = "splash") {
                        SplashScreen(
                            valid = valid,
                            onStart = mainViewModel::trackSplashScreenStarted,
                            onSplashEndedValid = {
                                navController.navigate("main") {
                                    popUpTo("splash") { inclusive = true }
                                }
                            },
                            onSplashEndedInvalid = {
                                Toast.makeText(
                                    context,
                                    "Something went wrong..",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        )
                    }
                    composable(route = "main") {
                        MainScreen(books = books, mainViewModel = mainViewModel)
                    }
                }
            }
        }
    }
}
