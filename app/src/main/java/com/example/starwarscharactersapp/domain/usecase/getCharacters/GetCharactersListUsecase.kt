package com.example.starwarscharactersapp.domain.usecase.getCharacters

import com.example.starwarscharactersapp.common.Resource
import com.example.starwarscharactersapp.data.data_source.CharacterListDao
import com.example.starwarscharactersapp.data.local.dto.toSWCharacter
import com.example.starwarscharactersapp.domain.model.SWCharacter
import com.example.starwarscharactersapp.domain.repository.Repository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetCharactersListUsecase @Inject constructor(
    private val repository: Repository,
    private val dao: CharacterListDao
) {
    operator fun invoke(pageNumber: Int): Flow<Resource<List<SWCharacter>>> = flow {
        try {
            emit(Resource.Loading<List<SWCharacter>>())
            val swCharactersFromDB: List<SWCharacter> = dao.getCharactersOfPage(pageNumber)
            if (swCharactersFromDB.isEmpty()) {
                val characters = repository.getSWCharacters(pageNumber).results
                characters.forEach { dao.insertSWCharacter(it.toSWCharacter(pageNumber)) }
                emit(Resource.Success<List<SWCharacter>>(characters.map {
                    it.toSWCharacter(
                        pageNumber
                    )
                }))
            } else {
                delay(1000) // just to show loader
                emit(Resource.Success<List<SWCharacter>>(swCharactersFromDB))
            }
        } catch (e: IOException) {
            emit(Resource.Error<List<SWCharacter>>("Couldn't Reach Server, Check your internet"))
        } catch (e: Exception) {
            emit(
                Resource.Error<List<SWCharacter>>(
                    e.localizedMessage ?: "Unexpected Error Occurred"
                )
            )
        }
    }
}