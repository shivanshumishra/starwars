package com.example.starwarscharactersapp.di

import android.app.Application
import androidx.room.Room
import com.example.starwarscharactersapp.common.Constants
import com.example.starwarscharactersapp.data.data_source.CharacterListDao
import com.example.starwarscharactersapp.data.data_source.CharactersDatabase
import com.example.starwarscharactersapp.data.repository.RepositoryImpl
import com.example.starwarscharactersapp.data.service.ApiService
import com.example.starwarscharactersapp.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApi() : ApiService {
        return Retrofit
            .Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(api: ApiService) : Repository {
        return RepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideDao(database: CharactersDatabase) : CharacterListDao {
        return database.sneakersDao
    }

    @Provides
    @Singleton
    fun providesSneakerDatabase(app: Application) : CharactersDatabase {
        return Room.databaseBuilder(
            app,
            CharactersDatabase::class.java,
            CharactersDatabase.DATABASE_NAME
        ).build()
    }
}