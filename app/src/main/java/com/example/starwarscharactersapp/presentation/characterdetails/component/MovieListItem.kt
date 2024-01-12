package com.example.starwarscharactersapp.presentation.characterdetails.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.starwarscharactersapp.common.Utils.capatalizeFirstLetter
import com.example.starwarscharactersapp.data.local.dto.Movie
import com.example.starwarscharactersapp.presentation.ui.theme.ColorSecondary
import com.example.starwarscharactersapp.presentation.ui.theme.MediumGray
import com.example.starwarscharactersapp.presentation.ui.theme.TextWhite

@Composable
fun MovieListItem(
    movie : Movie
){
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        ),
        modifier = Modifier.padding(8.dp),
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
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = movie.title.capatalizeFirstLetter(),
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = ColorSecondary
                )
            }
            Text(
                text = "Directed By : "+movie.director,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(
                    top = 8.dp
                )
            )
            Text(
                text = "Produced By : ${movie.producer}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = movie.opening_crawl,
                maxLines = 10,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "Released : ${movie.release_date}",
                style = MaterialTheme.typography.bodyMedium,
            )
        }

    }
}