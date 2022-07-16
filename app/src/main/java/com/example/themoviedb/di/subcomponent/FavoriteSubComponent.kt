package com.example.themoviedb.di.subcomponent

import com.example.themoviedb.view.Favorite
import com.example.themoviedb.di.scope.FavoriteScope
import com.example.themoviedb.di.viewmodel_module.FavoriteModule
import dagger.Subcomponent

@FavoriteScope
@Subcomponent(modules = [FavoriteModule::class])
interface FavoriteSubComponent {
    fun inject(fragment: Favorite)

    @Subcomponent.Factory
    interface Factory{
        fun create(): FavoriteSubComponent
    }
}