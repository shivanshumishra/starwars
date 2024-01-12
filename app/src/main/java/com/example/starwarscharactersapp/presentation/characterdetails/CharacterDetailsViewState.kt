package com.example.starwarscharactersapp.presentation.characterdetails

import com.example.starwarscharactersapp.data.local.dto.Movie

data class CharacterDetailsViewState(
    var isLoading : Boolean = false,
    var moviesList : List<Movie> = emptyList(),
    val error : String = "",
)
