package com.example.challengechap7.di.viewmodel_module

import com.dzakyhdr.moviedb.data.remote.MovieRepository
import com.example.challengechap7.di.scope.FavoriteScope
import com.example.challengechap7.view.viewmodelfactory.FavoriteViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class FavoriteModule {

    @FavoriteScope
    @Provides
    fun provideFavoriteViewModelFactory(
        movieRepository: MovieRepository,
    ): FavoriteViewModelFactory {
        return FavoriteViewModelFactory(movieRepository)
    }
}