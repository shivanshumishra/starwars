package com.example.starwarscharactersapp.common

sealed class Screen(val route:String) {
    object CharacterListScren : Screen("character_list_screen")
    object CharacterDetailScreen : Screen("character_detail_screen")
}
