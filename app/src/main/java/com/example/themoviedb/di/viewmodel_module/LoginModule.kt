package com.example.themoviedb.di.viewmodel_module

import com.example.themoviedb.data.local.auth.UserRepository
import com.example.themoviedb.di.scope.LoginScope
import com.example.themoviedb.view.viewmodelfactory.LoginViewModelFactory
import com.example.themoviedb.utils.UserDataManager
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