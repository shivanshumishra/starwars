package com.example.starwarscharactersapp.presentation.characterdetails

import android.app.Application
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.starwarscharactersapp.common.Utils.capatalizeFirstLetter
import com.example.starwarscharactersapp.domain.model.CharacterDetailModel
import com.example.starwarscharactersapp.presentation.ui.theme.ColorPrimary

@Composable
fun CharacterDetailScreen(
    navController: NavController,
    id:Int,
    viewModel: CharacterDetailViewModel = hiltViewModel()
){
    val state = viewModel.state.value
    LaunchedEffect(key1 = id ) {
        viewModel.getCharacterDetails(id)
    }
    if(state.isLoading){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                color = ColorPrimary
            )
        }
    } else {
        if(id == -1) {
            ShowEmptyDetailScreen()
        } else {
            CharacterDetailsView(state.characterDetails)
        }
    }
}

@Composable
fun CharacterDetailsView(details : CharacterDetailModel?) {

    val context = LocalContext.current

    details?.let { details ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "I am ", style = MaterialTheme.typography.titleLarge)
                Text(text = "${details.name}", style = MaterialTheme.typography.titleLarge, color = ColorPrimary)
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "I was born in ${details.birthYear}", style = MaterialTheme.typography.titleSmall)
                Text(text = "Gender : ${details.gender.capatalizeFirstLetter()}", style = MaterialTheme.typography.titleSmall)
            }

            Text(
                text = "Few details about me",
                modifier = Modifier.padding(top = 16.dp, bottom = 4.dp),
                style = MaterialTheme.typography.titleMedium
            )

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Height :"+details.height +"cms", style = MaterialTheme.typography.bodyMedium)
                Text(text = "Mass :"+details.mass, style = MaterialTheme.typography.bodyMedium)
//                Text(text = "Home World :"+details.homeworld, style = MaterialTheme.typography.bodyMedium)
                Text(text = "Skin Color :"+details.skinColor.capatalizeFirstLetter(), style = MaterialTheme.typography.bodyMedium)
                Text(text = "Eye Color :"+details.eyeColor.capatalizeFirstLetter(), style = MaterialTheme.typography.bodyMedium)
                Text(text = "Hair Color :"+details.hairColor.capatalizeFirstLetter(), style = MaterialTheme.typography.bodyMedium)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
             Column() {
                 Text(text = "Know more about my movies click below on one", style = MaterialTheme.typography.titleMedium)
                 LazyColumn( ) {
                     itemsIndexed(details.films) { index, film, ->
                         Row(
                             modifier = Modifier
                                 .clickable {
                                     Toast
                                         .makeText(
                                             context,
                                             "Details screen for film is yet to be added",
                                             Toast.LENGTH_LONG
                                         )
                                         .show()
                                 }
                             ,
                             horizontalArrangement = Arrangement.Center
                         ) {
                             Text(text = "Film ${index + 1}", color = Color.Blue)
                         }
                     }
                 }
             }
            }
        }
    }
}

@Composable
fun ShowEmptyDetailScreen() {
    TODO("Not yet implemented")
}
