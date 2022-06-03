package com.example.challengechap7.data.local.favorite

import androidx.room.*
import com.example.challengechap7.data.local.favorite.MovieEntity

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userEntity: MovieEntity)

    @Delete
    suspend fun delete(userEntity: MovieEntity)

    @Query("SELECT * from movieentity")
    suspend fun getFavorites(): List<MovieEntity>
}