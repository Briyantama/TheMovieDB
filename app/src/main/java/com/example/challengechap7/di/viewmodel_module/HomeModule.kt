package com.example.challengechap7.di.viewmodel_module

import com.example.challengechap7.data.local.auth.UserRepository
import com.dzakyhdr.moviedb.data.remote.MovieRepository
import com.example.challengechap7.di.scope.HomeScope
import com.example.challengechap7.view.viewmodelfactory.HomeViewModelFactory
import com.example.challengechap7.utils.UserDataManager
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