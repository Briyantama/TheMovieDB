package com.example.themoviedb.di.subcomponent

import com.example.themoviedb.di.scope.UpdateScope
import com.example.themoviedb.di.viewmodel_module.UpdateModule
import com.example.themoviedb.view.Profile
import dagger.Subcomponent

@UpdateScope
@Subcomponent(modules = [UpdateModule::class])
interface UpdateSubComponent {
    fun inject(fragment: Profile)

    @Subcomponent.Factory
    interface Factory{
        fun create(): UpdateSubComponent
    }
}