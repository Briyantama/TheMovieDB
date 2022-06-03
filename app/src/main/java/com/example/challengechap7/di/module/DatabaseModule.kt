package com.example.challengechap7.di.module

import android.content.Context
import androidx.room.Room
import com.example.challengechap7.data.local.auth.MovieDatabase
import com.example.challengechap7.data.local.auth.UserDao
import com.example.challengechap7.data.local.favorite.MovieDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideMovieDatabase(context: Context): MovieDatabase {
        return Room.databaseBuilder(context, MovieDatabase::class.java, "movie").build()
    }

    @Singleton
    @Provides
    fun provideMovieDao(movieDatabase: MovieDatabase): MovieDao {
        return movieDatabase.movieDao()
    }

    @Singleton
    @Provides
    fun provideUserDao(movieDatabase: MovieDatabase): UserDao {
        return movieDatabase.userDao()
    }
}