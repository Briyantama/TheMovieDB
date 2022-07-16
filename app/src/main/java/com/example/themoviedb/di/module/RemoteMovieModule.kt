package com.example.themoviedb.di.module

import com.example.themoviedb.data.remote.MovieRemoteDataSource
import com.example.themoviedb.network.ApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteMovieModule(private val apiKey: String) {
    @Singleton
    @Provides
    fun provideMovieRemoteDataSource(apiService: ApiService): MovieRemoteDataSource {
        return MovieRemoteDataSource(apiService, apiKey)
    }
}