package com.example.themoviedb.di.subcomponent

import com.example.themoviedb.di.module.*
import com.example.themoviedb.di.viewmodel_module.AppModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        DatabaseModule::class,
        DataStoreModule::class,
        LocalMovieModule::class,
        NetworkModule::class,
        RemoteMovieModule::class,
        RepositoryModule::class
    ]
)
interface AppComponent{
    fun detailSubComponent(): DetailSubComponent.Factory
    fun favoriteSubComponent(): FavoriteSubComponent.Factory
    fun homeSubComponent(): HomeSubComponent.Factory
    fun loginSubComponent(): LoginSubComponent.Factory
    fun registerSubComponent(): RegisterSubComponent.Factory
    fun updateSubComponent(): UpdateSubComponent.Factory
}