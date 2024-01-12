package com.example.starwarscharactersapp.data.service

import com.example.starwarscharactersapp.data.local.dto.MoviesDetailsResponse
import com.example.starwarscharactersapp.domain.model.ListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("people/")
    suspend fun getSWCharacters(
        @Query("page") pageNumber: Int
    ) : ListResponse

    @GET("films/")
    suspend fun getCharacterMovieDetails() : MoviesDetailsResponse
}