package com.example.challengechap7.di.viewmodel_module

import com.example.challengechap7.data.local.auth.UserRepository
import com.example.challengechap7.di.scope.LoginScope
import com.example.challengechap7.view.viewmodelfactory.LoginViewModelFactory
import com.example.challengechap7.utils.UserDataManager
import dagger.Module
import dagger.Provides

@Module
class LoginModule {
    @LoginScope
    @Provides
    fun provideLoginViewModelFactory(
        userRepository: UserRepository,
        pref: UserDataManager
    ): LoginViewModelFactory {
        return LoginViewModelFactory(userRepository, pref)
    }
}