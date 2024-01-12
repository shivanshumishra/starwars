package com.example.starwarscharactersapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class SWCharacter(
    @PrimaryKey(autoGenerate = true)
    val id :Int? = null,
    @SerializedName("birth_year")
    val birthYear: String,
    val created: String,
    val edited: String,
    val gender: String,
    val name: String,
    val url: String,
    val page: Int? = null
)
