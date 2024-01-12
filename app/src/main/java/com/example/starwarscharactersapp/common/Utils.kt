package com.example.starwarscharactersapp.common

import androidx.compose.ui.text.capitalize
import java.util.Locale

object Utils {
    fun String.capatalizeFirstLetter() : String {
        return this.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
    }
}