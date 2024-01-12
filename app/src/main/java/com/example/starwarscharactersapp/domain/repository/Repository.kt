package com.example.starwarscharactersapp.domain.repository

import com.example.starwarscharactersapp.data.local.dto.CharacterDetailsDto
import com.example.starwarscharactersapp.domain.model.ListResponse

interface Repository {
    suspend fun getSWCharacters(pageNumber :Int) : ListResponse

    suspend fun getCharacterDetails(id : Int) : CharacterDetailsDto?
}