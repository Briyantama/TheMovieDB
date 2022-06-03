package com.example.challengechap7.di.module

import com.example.challengechap7.data.local.MovieLocalDataSource
import com.example.challengechap7.data.local.favorite.MovieDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalMovieModule {

    @Singleton
    @Provides
    fun provideMovieLocalDataSource(movieDao: MovieDao): MovieLocalDataSource {
        return MovieLocalDataSource(movieDao)
    }
}