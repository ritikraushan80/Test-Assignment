package com.pw.pwjetpackapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pw.pwjetpackapp.ui.CharacterDetailScreen
import com.pw.pwjetpackapp.ui.CharacterListScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
            ) {
                val navController = rememberNavController()
                NavHost(navController, startDestination = "character_list") {
                    composable("character_list") {
                        CharacterListScreen(
                            navController = navController,
                            onCharacterClick = { character ->
                                navController.navigate("character_detail/${character.id}")
                            }
                        )
                    }
                    composable("character_detail/{id}") { backStackEntry ->
                        val characterId = backStackEntry.arguments?.getLong("id")
                        CharacterDetailScreen(characterId = characterId?.toInt() ?: 0)
                    }
                }
            }
        }
    }

}