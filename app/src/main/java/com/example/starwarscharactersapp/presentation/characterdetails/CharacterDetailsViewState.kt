package com.example.starwarscharactersapp.presentation.characterdetails

import com.example.starwarscharactersapp.domain.model.CharacterDetailModel
import com.example.starwarscharactersapp.domain.model.SWCharacter

data class CharacterDetailsViewState(
    var isLoading : Boolean = false,
    var characterDetails : CharacterDetailModel? = null,
    val error : String = "",
)
