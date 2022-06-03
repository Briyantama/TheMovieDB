package com.example.challengechap7.di.module

import com.example.challengechap7.data.remote.MovieRemoteDataSource
import com.example.challengechap7.network.ApiService
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