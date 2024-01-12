package com.example.starwarscharactersapp.presentation.characterdetails

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarscharactersapp.common.Resource
import com.example.starwarscharactersapp.domain.usecase.getcharacterdetails.GetCharacterMovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CharacterMoviesViewModel @Inject constructor(
    val getCharacterMovieDetailsUseCase: GetCharacterMovieDetailsUseCase
) : ViewModel() {

    private val _state = mutableStateOf(CharacterDetailsViewState())
    val state: State<CharacterDetailsViewState> = _state

    fun getCharacterDetails(id : Int) {
        getCharacterMovieDetailsUseCase(id).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    result.data?.let {  list ->
                        _state.value = CharacterDetailsViewState(
                            moviesList = list
                        )
                    }
                }

                is Resource.Error -> {
                    _state.value = CharacterDetailsViewState(
                        error = result.message ?: "Unexpected error occurred"
                    )
                }

                is Resource.Loading -> {
                    _state.value = CharacterDetailsViewState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}