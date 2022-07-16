package com.example.themoviedb.di.module

import android.content.Context
import com.example.themoviedb.utils.UserDataManager
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