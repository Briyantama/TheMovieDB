package com.example.challengechap7.di.subcomponent

import com.example.challengechap7.di.scope.LoginScope
import com.example.challengechap7.di.viewmodel_module.LoginModule
import com.example.challengechap7.view.Login
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