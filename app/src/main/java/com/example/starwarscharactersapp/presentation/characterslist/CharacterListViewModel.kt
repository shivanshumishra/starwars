package com.example.starwarscharactersapp.presentation.characterslist

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarscharactersapp.common.Resource
import com.example.starwarscharactersapp.domain.model.SWCharacter
import com.example.starwarscharactersapp.domain.usecase.getCharacters.GetCharactersListUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    val getCharactersListUsecase: GetCharactersListUsecase
) : ViewModel() {

    val currentPage = mutableIntStateOf(1)
    private val _state = mutableStateOf(CharacterListViewState())
    val state: State<CharacterListViewState> = _state
    val selectedFilter : MutableState<String> = mutableStateOf("")
    val selectedSort : MutableState<String> = mutableStateOf("")

    init {
        getCharactersList()
    }

    fun sortItems() {
        _state.value.isLoading = true
        when (selectedSort.value) {
            "Name" -> {
                _state.value.characters = _state.value.characters.sortedBy { it.name }.toMutableList()
            }
            "Created" -> {
                _state.value.characters = _state.value.characters.sortedBy { it.created }.toMutableList()
            }
            "Updated" -> {
                _state.value.characters = _state.value.characters.sortedBy { it.edited }.toMutableList()
            }
            else -> {
                getCharactersList()
            }
        }
        _state.value.isLoading = false
    }

    fun filterItems() {
        getCharactersList()
    }

    fun getCharactersList() {
        getCharactersListUsecase(currentPage.value).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    selectedSort.value = ""
                    result.data?.let { data ->
                        val list : MutableList<SWCharacter>
                        if(_state.value.characters.isEmpty()){
                            list = data.toMutableList()
                        } else {
                            list = _state.value.characters
                            list.addAll(data)
                        }
                        when (selectedFilter.value) {
                            "Gender : Male" -> {
                                _state.value = CharacterListViewState(
                                    characters = list.filter { it.gender == "male" }.toMutableList()
                                )
                            }

                            "Gender : Female" -> {
                                _state.value = CharacterListViewState(
                                    characters = list.filter { it.gender == "female" }.toMutableList()
                                )
                            }

                            "Gender : Not Applicable" -> {
                                _state.value = CharacterListViewState(
                                    characters = list.filter { it.gender == "n/a" }.toMutableList()
                                )
                            }

                            else -> {
                                _state.value = CharacterListViewState(
                                    characters = list
                                )
                            }
                        }
                    }
                }

                is Resource.Error -> {
                    _state.value = CharacterListViewState(
                        error = result.message ?: "Unexpected error occurred"
                    )
                }

                is Resource.Loading -> {
                    _state.value = CharacterListViewState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}