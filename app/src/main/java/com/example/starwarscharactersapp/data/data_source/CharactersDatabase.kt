package com.example.starwarscharactersapp.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.starwarscharactersapp.domain.model.SWCharacter

@Database(
    entities = [SWCharacter::class],
    version = 1
)
abstract class CharactersDatabase : RoomDatabase(){
    abstract val sneakersDao : CharacterListDao

    companion object {
        const val DATABASE_NAME = "characters_db"
    }
}