package com.example.challengechap7.di.subcomponent

import com.example.challengechap7.di.scope.UpdateScope
import com.example.challengechap7.di.viewmodel_module.UpdateModule
import com.example.challengechap7.view.Profile
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