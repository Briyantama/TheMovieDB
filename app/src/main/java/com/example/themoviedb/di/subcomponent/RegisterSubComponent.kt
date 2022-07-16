package com.example.themoviedb.di.subcomponent

import com.example.themoviedb.di.scope.RegisterScope
import com.example.themoviedb.di.viewmodel_module.RegisterModule
import com.example.themoviedb.view.Register
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