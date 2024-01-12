package com.example.starwarscharactersapp.data.local.dto

import com.example.starwarscharactersapp.domain.model.CharacterDetailModel
import com.google.gson.annotations.SerializedName

data class CharacterDetailsDto(
    @SerializedName("birth_year")
    val birthYear: String = "",
    val created: String = "",
    val edited: String = "",
    @SerializedName("eye_color")
    val eyeColor: String = "",
    val films: List<String> = emptyList(),
    val gender: String = "",
    @SerializedName("hair_color")
    val hairColor: String = "",
    val height: String = "",
    val homeworld: String = "",
    val mass: String = "",
    val name: String = "",
    @SerializedName("skin_color")
    val skinColor: String = "",
    val species: List<String> = emptyList(),
    val starships: List<String> = emptyList(),
    val url: String = "",
    val vehicles: List<String> = emptyList()
)

fun CharacterDetailsDto.toCharacterDetailModel() : CharacterDetailModel {
    return CharacterDetailModel(birthYear, created, edited, eyeColor, films, gender, hairColor, height, homeworld, mass, name, skinColor)
}