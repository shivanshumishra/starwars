package com.example.starwarscharactersapp.data.local.dto

import com.example.starwarscharactersapp.domain.model.SWCharacter
import com.google.gson.annotations.SerializedName

data class SWCharacterDto(
    @SerializedName("birth_year")
    val birthYear: String,
    val created: String,
    val edited: String,
    @SerializedName("eye_color")
    val eyeColor: String,
    val films: List<String>,
    val gender: String,
    @SerializedName("hair_color")
    val hairColor: String,
    val height: String,
    val homeWorld: String,
    val mass: String,
    val name: String,
    @SerializedName("skin_color")
    val skinColor: String,
    val species: List<String>,
    val starships: List<String>,
    val url: String,
    val vehicles: List<String>
)

fun SWCharacterDto.toSWCharacter(): SWCharacter {
    return SWCharacter(
        birthYear = birthYear,
        created = created,
        edited = edited,
        gender = gender,
        name = name,
        url = url
    )
}