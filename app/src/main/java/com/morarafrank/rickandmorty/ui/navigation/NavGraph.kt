package com.morarafrank.rickandmorty.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.morarafrank.rickandmorty.ui.screens.CharactersScreen
import com.morarafrank.rickandmorty.ui.screens.SingleCharacterScreen

@Composable
fun RickAndMortyNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {


    // Navigation graph implementation will go here
    NavHost(
        modifier = modifier,
        startDestination = Screens.CharactersScreen.route,
        navController = navController
    ) {

        composable(
            route = Screens.CharactersScreen.route
        ){
            CharactersScreen(
                navigateToCharacter = { id ->
                    navController.navigate(
                        route = "${Screens.SingleCharacterScreen.route}/$id"
                    )
                }
            )
        }

        composable(
            route = "${Screens.SingleCharacterScreen.route}/{id}",
            arguments = listOf(
                navArgument("id"){
                    type = androidx.navigation.NavType.IntType
                }
            )
        ){

            SingleCharacterScreen(
                id = it.arguments?.getInt("id") ?: 0,
                navigateBack = {
                    navController.navigateUp()
                }

            )
        }



    }
}