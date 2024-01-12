package com.example.starwarscharactersapp.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.starwarscharactersapp.domain.model.SWCharacter

@Dao
interface CharacterListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSWCharacter(swCharacter: SWCharacter)

    @Query("SELECT * from swcharacter where page=:page")
    suspend fun getCharactersOfPage(page:Int) : List<SWCharacter>
}