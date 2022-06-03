package com.example.challengechap7.di.viewmodel_module

import com.example.challengechap7.data.local.auth.UserRepository
import com.example.challengechap7.di.scope.RegisterScope
import com.example.challengechap7.view.viewmodelfactory.RegisterViewModelFactory
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