package com.example.starwarscharactersapp.presentation.characterdetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.starwarscharactersapp.R
import com.example.starwarscharactersapp.data.local.dto.Movie
import com.example.starwarscharactersapp.presentation.characterdetails.component.MovieListItem
import com.example.starwarscharactersapp.presentation.ui.theme.ColorPrimary

@Composable
fun CharacterMovieScreen(
    id: Int,
    viewModel: CharacterMoviesViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    LaunchedEffect(key1 = id) {
        viewModel.getCharacterDetails(id)
    }
    if (state.isLoading) {
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
        if (id == -1) {
            ShowEmptyDetailScreen()
        } else {
            CharacterMoviesView(state.moviesList)
        }
    }
}

@Composable
fun CharacterMoviesView(movies: List<Movie>) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(2)
    ) {
        items(movies) { movie ->
            MovieListItem(movie = movie)
        }
    }
}

@Composable
fun ShowEmptyDetailScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(id = R.string.empty_moview_detail))
    }
}
