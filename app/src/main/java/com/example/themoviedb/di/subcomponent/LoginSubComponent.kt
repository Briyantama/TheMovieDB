package com.example.themoviedb.di.subcomponent

import com.example.themoviedb.di.scope.LoginScope
import com.example.themoviedb.di.viewmodel_module.LoginModule
import com.example.themoviedb.view.Login
import dagger.Subcomponent

@LoginScope
@Subcomponent(modules = [LoginModule::class])
interface LoginSubComponent {
    fun inject(fragment: Login)

    @Subcomponent.Factory
    interface Factory{
        fun create(): LoginSubComponent
    }
}