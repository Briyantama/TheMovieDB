package com.example.challengechap7.di.subcomponent

import com.example.challengechap7.view.Favorite
import com.example.challengechap7.di.scope.FavoriteScope
import com.example.challengechap7.di.viewmodel_module.FavoriteModule
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