package com.example.challengechap7.di.module

import android.content.Context
import com.example.challengechap7.utils.UserDataManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataStoreModule {

    @Singleton
    @Provides
    fun provideUserDataStoreManager(context: Context): UserDataManager {
        return UserDataManager(context)
    }
}