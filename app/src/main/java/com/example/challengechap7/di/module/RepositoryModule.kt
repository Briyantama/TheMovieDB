package com.example.challengechap7.di.module

import com.example.challengechap7.data.local.MovieLocalDataSource
import com.example.challengechap7.data.local.auth.UserDao
import com.example.challengechap7.data.local.auth.UserRepository
import com.example.challengechap7.data.remote.MovieRemoteDataSource
import com.dzakyhdr.moviedb.data.remote.MovieRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideUserRepository(userDao: UserDao): UserRepository {
        return UserRepository(userDao)
    }

    @Singleton
    @Provides
    fun provideMovieRepository(
        movieRemoteDataSource: MovieRemoteDataSource,
        movieLocalDataSource: MovieLocalDataSource
    ): MovieRepository {
        return MovieRepository(movieRemoteDataSource, movieLocalDataSource)
    }
}