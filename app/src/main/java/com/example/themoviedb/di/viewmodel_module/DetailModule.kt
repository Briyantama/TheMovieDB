package com.example.themoviedb.di.viewmodel_module

import com.dzakyhdr.moviedb.data.remote.MovieRepository
import com.example.themoviedb.di.scope.DetailScope
import com.example.themoviedb.view.viewmodelfactory.DetailViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class DetailModule {

    @DetailScope
    @Provides
    fun provideDetailViewModelFactory(
        movieRepository: MovieRepository,
    ): DetailViewModelFactory {
        return DetailViewModelFactory(movieRepository)
    }
}