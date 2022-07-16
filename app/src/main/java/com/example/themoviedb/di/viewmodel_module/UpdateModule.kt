package com.example.themoviedb.di.viewmodel_module

import com.example.themoviedb.data.local.auth.UserRepository
import com.example.themoviedb.di.scope.UpdateScope
import com.example.themoviedb.utils.UserDataManager
import com.example.themoviedb.view.viewmodelfactory.UpdateViewModelFactory
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