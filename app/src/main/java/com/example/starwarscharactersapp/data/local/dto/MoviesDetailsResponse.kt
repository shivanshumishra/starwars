package com.example.starwarscharactersapp.data.local.dto

data class MoviesDetailsResponse(
    val count: Int,
    val next: Any,
    val previous: Any,
    val results: List<Movie>
)