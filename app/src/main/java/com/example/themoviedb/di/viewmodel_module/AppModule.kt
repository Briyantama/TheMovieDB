package com.example.themoviedb.di.viewmodel_module

import android.content.Context
import com.example.themoviedb.di.subcomponent.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    subcomponents = [
        DetailSubComponent::class,
        FavoriteSubComponent::class,
        HomeSubComponent::class,
        LoginSubComponent::class,
        RegisterSubComponent::class,
        UpdateSubComponent::class]
)
class AppModule(private val context: Context) {
    @Singleton
    @Provides
    fun provideApplicationContext(): Context {
        return context.applicationContext
    }
}