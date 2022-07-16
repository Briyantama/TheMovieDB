package com.example.themoviedb.data.remote

import com.example.themoviedb.data.remote.model.popular.Result
import com.example.themoviedb.network.ApiService

class MovieRemoteDataSource(private val service: ApiService, private val apiKey: String) {

    suspend fun getMovies(): List<Result>? {
        try {
            return service.getPopular(apiKey).results
        } catch (cause: Throwable) {
            throw ErrorMovie("Data Gagal Diload", cause)
        }
    }

    suspend fun getDetail(id: Int): Result {
        try {
            return service.getDetail(movieId = id, api = apiKey).body()!!
        } catch (cause: Throwable) {
            throw ErrorMovie("Ada kesalahan saat load detail", cause)
        }
    }

}

