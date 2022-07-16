package com.example.themoviedb.di.myapp

import com.example.themoviedb.di.subcomponent.*

interface Injector{
    fun createDetailSubComponent(): DetailSubComponent
    fun createFavoriteSubComponent(): FavoriteSubComponent
    fun createHomeSubComponent(): HomeSubComponent
    fun createLoginSubComponent(): LoginSubComponent
    fun createRegisterSubComponent(): RegisterSubComponent
    fun createUpdateSubComponent(): UpdateSubComponent
}