package com.example.themoviedb.di.subcomponent

import com.example.themoviedb.di.scope.HomeScope
import com.example.themoviedb.di.viewmodel_module.HomeModule
import com.example.themoviedb.view.Home
import dagger.Subcomponent

@HomeScope
@Subcomponent(modules = [HomeModule::class])
interface HomeSubComponent {
    fun inject(fragment: Home)

    @Subcomponent.Factory
    interface Factory{
        fun create(): HomeSubComponent
    }
}