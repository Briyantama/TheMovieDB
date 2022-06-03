package com.example.challengechap7.di.subcomponent

import com.example.challengechap7.di.scope.RegisterScope
import com.example.challengechap7.di.viewmodel_module.RegisterModule
import com.example.challengechap7.view.Register
import dagger.Subcomponent

@RegisterScope
@Subcomponent(modules = [RegisterModule::class])
interface RegisterSubComponent {
    fun inject(fragment: Register)

    @Subcomponent.Factory
    interface Factory{
        fun create(): RegisterSubComponent
    }
}