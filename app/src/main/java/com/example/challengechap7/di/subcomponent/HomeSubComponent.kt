package com.example.challengechap7.di.subcomponent

import com.example.challengechap7.di.scope.HomeScope
import com.example.challengechap7.di.viewmodel_module.HomeModule
import com.example.challengechap7.view.Home
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