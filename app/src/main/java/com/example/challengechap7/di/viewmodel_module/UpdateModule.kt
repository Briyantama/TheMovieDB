package com.example.challengechap7.di.viewmodel_module

import com.example.challengechap7.data.local.auth.UserRepository
import com.example.challengechap7.di.scope.UpdateScope
import com.example.challengechap7.utils.UserDataManager
import com.example.challengechap7.view.viewmodelfactory.UpdateViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class UpdateModule {
    @UpdateScope
    @Provides
    fun provideUpdateViewModelFactory(
        userRepository: UserRepository,
        pref: UserDataManager
    ): UpdateViewModelFactory {
        return UpdateViewModelFactory(userRepository, pref)
    }
}