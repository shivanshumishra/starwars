package com.example.starwarscharactersapp.presentation.characterslist

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.starwarscharactersapp.domain.model.SWCharacter

data class CharacterListViewState(
    var isLoading : Boolean = false,
    var characters : List<SWCharacter> = emptyList(),
    val error : String = "",
)
