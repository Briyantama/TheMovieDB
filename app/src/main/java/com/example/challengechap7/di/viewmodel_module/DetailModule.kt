package com.example.challengechap7.di.viewmodel_module

import com.dzakyhdr.moviedb.data.remote.MovieRepository
import com.example.challengechap7.di.scope.DetailScope
import com.example.challengechap7.view.viewmodelfactory.DetailViewModelFactory
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