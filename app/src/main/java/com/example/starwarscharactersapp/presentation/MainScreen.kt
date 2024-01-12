package com.example.starwarscharactersapp.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.starwarscharactersapp.common.Screen
import com.example.starwarscharactersapp.presentation.characterdetails.CharacterMovieScreen
import com.example.starwarscharactersapp.presentation.characterslist.CharacterListScreen
import com.example.starwarscharactersapp.presentation.ui.theme.DarkGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(){

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = DarkGray
    ) {
        val navController = rememberNavController()

        Scaffold(
            bottomBar = {

            },
            containerColor = DarkGray
        ) { innerPadding ->
            Box(
                modifier = Modifier.padding(
                    PaddingValues(
                        0.dp,
                        0.dp,
                        0.dp,
                        innerPadding.calculateBottomPadding()
                    )
                )
            ) {
                NavHost(navController, startDestination = Screen.CharacterListScren.route) {
                    composable(Screen.CharacterListScren.route) {
                        CharacterListScreen(navController)
                    }
                    composable(Screen.CharacterDetailScreen.route +"/{id}", arguments = listOf(navArgument("id"){type = NavType.IntType})) {
                        val id = it.arguments?.getInt("id") ?: -1
                        CharacterMovieScreen(id)
                    }

                }
            }
        }
    }
}