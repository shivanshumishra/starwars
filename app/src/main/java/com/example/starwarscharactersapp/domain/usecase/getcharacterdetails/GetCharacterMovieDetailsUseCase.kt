package com.example.starwarscharactersapp.domain.usecase.getcharacterdetails

import com.example.starwarscharactersapp.common.Resource
import com.example.starwarscharactersapp.data.local.dto.Movie
import com.example.starwarscharactersapp.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetCharacterMovieDetailsUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(id: Int): Flow<Resource<List<Movie>>> = flow {
        try {
            emit(Resource.Loading<List<Movie>>())
            val moviesDetails = repository.getCharacterMovieDetails()
            val list : MutableList<Movie> = mutableListOf()
            moviesDetails.results.forEach {result ->
                result.characters.forEach {
                    if(it.contains("people/${id}/")){
                        list.add(result)
                    }
                }
            }
            emit(Resource.Success<List<Movie>>(list.toList()))
        } catch (e: IOException) {
            emit(Resource.Error<List<Movie>>("Couldn't Reach Server, Check your internet"))
        } catch (e: Exception) {
            emit(
                Resource.Error<List<Movie>>(
                    e.localizedMessage ?: "Unexpected Error Occurred"
                )
            )
        }
    }
}