package com.example.challengechap7.data.local.auth

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.challengechap7.data.local.favorite.MovieDao
import com.example.challengechap7.data.local.favorite.MovieEntity

@Database(entities = [User::class, MovieEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun movieDao(): MovieDao
}