package com.example.themoviedb.di.viewmodel_module

import com.dzakyhdr.moviedb.data.remote.MovieRepository
import com.example.themoviedb.di.scope.FavoriteScope
import com.example.themoviedb.view.viewmodelfactory.FavoriteViewModelFactory
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