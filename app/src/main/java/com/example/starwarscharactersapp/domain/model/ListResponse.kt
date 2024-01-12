package com.example.starwarscharactersapp.domain.model

import com.example.starwarscharactersapp.data.local.dto.SWCharacterDto

data class ListResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<SWCharacterDto>
)
