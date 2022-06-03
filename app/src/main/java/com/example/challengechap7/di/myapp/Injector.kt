package com.example.challengechap7.di.myapp

import com.example.challengechap7.di.subcomponent.*

interface Injector{
    fun createDetailSubComponent(): DetailSubComponent
    fun createFavoriteSubComponent(): FavoriteSubComponent
    fun createHomeSubComponent(): HomeSubComponent
    fun createLoginSubComponent(): LoginSubComponent
    fun createRegisterSubComponent(): RegisterSubComponent
    fun createUpdateSubComponent(): UpdateSubComponent
}