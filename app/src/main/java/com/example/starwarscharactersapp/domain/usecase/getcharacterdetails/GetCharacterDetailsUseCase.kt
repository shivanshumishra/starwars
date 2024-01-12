package com.example.starwarscharactersapp.domain.usecase.getcharacterdetails

import com.example.starwarscharactersapp.common.Resource
import com.example.starwarscharactersapp.data.data_source.CharacterListDao
import com.example.starwarscharactersapp.data.local.dto.CharacterDetailsDto
import com.example.starwarscharactersapp.data.local.dto.toCharacterDetailModel
import com.example.starwarscharactersapp.data.local.dto.toSWCharacter
import com.example.starwarscharactersapp.domain.model.CharacterDetailModel
import com.example.starwarscharactersapp.domain.model.SWCharacter
import com.example.starwarscharactersapp.domain.repository.Repository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetCharacterDetailsUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(id: Int): Flow<Resource<CharacterDetailModel>> = flow {
        try {
            emit(Resource.Loading<CharacterDetailModel>())
            val characterDetail = repository.getCharacterDetails(id) ?: CharacterDetailsDto()
            emit(Resource.Success<CharacterDetailModel>(characterDetail.toCharacterDetailModel()))
        } catch (e: IOException) {
            emit(Resource.Error<CharacterDetailModel>("Couldn't Reach Server, Check your internet"))
        } catch (e: Exception) {
            emit(
                Resource.Error<CharacterDetailModel>(
                    e.localizedMessage ?: "Unexpected Error Occurred"
                )
            )
        }
    }
}