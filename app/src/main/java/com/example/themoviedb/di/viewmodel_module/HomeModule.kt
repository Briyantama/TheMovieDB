package com.example.themoviedb.di.viewmodel_module

import com.example.themoviedb.data.local.auth.UserRepository
import com.dzakyhdr.moviedb.data.remote.MovieRepository
import com.example.themoviedb.di.scope.HomeScope
import com.example.themoviedb.view.viewmodelfactory.HomeViewModelFactory
import com.example.themoviedb.utils.UserDataManager
import dagger.Module
import dagger.Provides

@Module
class HomeModule {

    @HomeScope
    @Provides
    fun provideHomeViewModelFactory(
        movieRepository: MovieRepository,
        userRepository: UserRepository,
        pref: UserDataManager
    ): HomeViewModelFactory {
        return HomeViewModelFactory(movieRepository, userRepository, pref)
    }
}