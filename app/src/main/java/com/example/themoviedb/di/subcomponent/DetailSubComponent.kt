package com.example.themoviedb.di.subcomponent

import com.example.themoviedb.di.scope.DetailScope
import com.example.themoviedb.di.viewmodel_module.DetailModule
import com.example.themoviedb.view.Detail
import dagger.Subcomponent

@DetailScope
@Subcomponent(modules = [DetailModule::class])
interface DetailSubComponent {
    fun inject(fragment: Detail)

    @Subcomponent.Factory
    interface Factory{
        fun create(): DetailSubComponent
    }
}