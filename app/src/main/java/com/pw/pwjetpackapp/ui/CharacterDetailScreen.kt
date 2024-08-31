package com.pw.pwjetpackapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pw.pwjetpackapp.viewmodel.CharacterDetailViewModel


/**
 * Created by Ritik on: 31/08/24
 */

@Composable
fun CharacterDetailScreen(
    characterId: Int
) {
   val viewModel: CharacterDetailViewModel = viewModel()
    val characterDetail by viewModel.characterDetail.observeAsState(initial = null)

    if (characterDetail != null) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
           characterDetail.let {
               Text(it?.name?:"")
               Text(it?.status?:"")
               Text(it?.species?:"")
               Text(it?.type?:"")
               Text(it?.gender?:"")
               Text(it?.origin?.name?:"")
               Text(it?.location?.name?:"")
               Text(it?.image?:"")
               Text(it?.episode?.joinToString(", ")?:"")
               Text(it?.url?:"")
               Text(it?.created?:"")
           }
        }
    } else {
        CircularProgressIndicator()
    }
}