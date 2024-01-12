package com.example.starwarscharactersapp.common

sealed class SortType(val sortType : String)
object NAME : SortType("Name")
object CREATED : SortType("Created")
object UPDATED : SortType("Updated")
object None : SortType("None")