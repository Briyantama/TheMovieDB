package com.example.challengechap7.data.local

import com.example.challengechap7.data.local.favorite.MovieDao
import com.example.challengechap7.data.local.favorite.MovieEntity

class MovieLocalDataSource(private val movieDao: MovieDao) {
    suspend fun insert(movie: MovieEntity) {
        movieDao.insert(movie)
    }

    suspend fun delete(movie: MovieEntity) = movieDao.delete(movie)

    suspend fun getFavoriteMovie(): List<MovieEntity> = movieDao.getFavorites()
}