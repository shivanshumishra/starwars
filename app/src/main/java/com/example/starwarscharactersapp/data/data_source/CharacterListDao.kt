package com.example.starwarscharactersapp.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.starwarscharactersapp.domain.model.SWCharacter
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

@Dao
interface CharacterListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSWCharacter(swCharacter: SWCharacter)

    @Query("SELECT * from swcharacter")
    suspend fun getSneakersInCart() : List<SWCharacter>
}