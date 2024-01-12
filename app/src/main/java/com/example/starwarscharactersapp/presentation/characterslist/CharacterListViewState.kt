package com.example.starwarscharactersapp.presentation.characterslist

import com.example.starwarscharactersapp.domain.model.SWCharacter

data class CharacterListViewState(
    var isLoading : Boolean = false,
    var characters : MutableList<SWCharacter> = mutableListOf(),
    val error : String = "",
)
