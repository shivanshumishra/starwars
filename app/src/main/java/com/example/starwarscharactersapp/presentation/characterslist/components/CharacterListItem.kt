package com.example.starwarscharactersapp.presentation.characterslist.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.starwarscharactersapp.common.Utils.capatalizeFirstLetter
import com.example.starwarscharactersapp.domain.model.SWCharacter
import com.example.starwarscharactersapp.presentation.ui.theme.ColorSecondary
import com.example.starwarscharactersapp.presentation.ui.theme.MediumGray
import com.example.starwarscharactersapp.presentation.ui.theme.TextWhite
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterListItem(
    character : SWCharacter,
    showCharacterDetails : (SWCharacter) -> Unit
){
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        ),
        modifier = Modifier.padding(8.dp),
        onClick = {
            showCharacterDetails(character)
        },
        colors = CardDefaults.cardColors(
            containerColor = MediumGray,
            contentColor = TextWhite
        )
    ) {

            Column (
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ){
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = character.name.capatalizeFirstLetter(),
                        style = MaterialTheme.typography.labelMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = ColorSecondary
                    )
                }
                Text(
                    text = "Year of Birth : ${character.birthYear}",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(
                        top = 8.dp
                    )
                )
                Text(
                    text = if (character.gender != "n/a") "Gender ${character.gender.capatalizeFirstLetter()}" else "Gender : N/A",
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = "Created : ${character.created.split("T")[0]}",
                    style = MaterialTheme.typography.bodySmall,
                )
                Text(
                    text = "Edited : ${character.edited.split("T")[0]}",
                    style = MaterialTheme.typography.bodySmall,
                )
            }

    }
}