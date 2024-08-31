package com.pw.pwjetpackapp.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.pw.pwjetpackapp.model.Results
import com.pw.pwjetpackapp.viewmodel.CharacterListViewModel

/**
 * Created by Ritik on: 31/08/24
 */

@Composable
fun CharacterListScreen(
    onCharacterClick: (Results) -> Unit,
    navController: NavController
) {
    val viewModel: CharacterListViewModel = viewModel()
    val characters by viewModel.characters.observeAsState(initial = emptyList())
    val isLoading by viewModel.isLoading.observeAsState(initial = false)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        if (isLoading) {
            CircularProgressIndicator()
        } else {
            characters.forEach { character ->
                CharacterListItem(character, onCharacterClick, navController)
            }
        }
    }
}

@Composable
fun CharacterListItem(
    character: Results,
    onCharacterClick: (Results) -> Unit,
    navController: NavController
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(onClick = {
                onCharacterClick(character)
                navController.navigate("character_detail/${character.id}")
            })
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        ) {
            Text(character.name?:"")
            Text(character.status?:"")
        }
    }
}