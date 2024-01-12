package com.example.starwarscharactersapp.data.repository

import com.example.starwarscharactersapp.data.local.dto.CharacterDetailsDto
import com.example.starwarscharactersapp.data.local.dto.SWCharacterDto
import com.example.starwarscharactersapp.data.service.ApiService
import com.example.starwarscharactersapp.domain.model.ListResponse
import com.example.starwarscharactersapp.domain.repository.Repository
import retrofit2.Call
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    val api: ApiService
) : Repository {
    override suspend fun getSWCharacters(pageNumber: Int): ListResponse {
        return api.getSWCharacters(pageNumber)
    }

    override suspend fun getCharacterDetails(id: Int): CharacterDetailsDto? {
        return api.getCharacterDetails(id)
    }
}