package com.example.themoviedb.di.viewmodel_module

import com.example.themoviedb.data.local.auth.UserRepository
import com.example.themoviedb.di.scope.RegisterScope
import com.example.themoviedb.view.viewmodelfactory.RegisterViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class RegisterModule {
    @RegisterScope
    @Provides
    fun provideRegisterViewModelFactory(
        userRepository: UserRepository,
    ): RegisterViewModelFactory {
        return RegisterViewModelFactory(userRepository)
    }
}