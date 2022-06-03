package com.example.challengechap7.di.subcomponent

import com.example.challengechap7.di.scope.DetailScope
import com.example.challengechap7.di.viewmodel_module.DetailModule
import com.example.challengechap7.view.Detail
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