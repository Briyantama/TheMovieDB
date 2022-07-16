package com.example.themoviedb.di.module

import com.example.themoviedb.data.local.MovieLocalDataSource
import com.example.themoviedb.data.local.favorite.MovieDao
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