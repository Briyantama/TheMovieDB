package com.example.challengechap7.network

import com.example.challengechap7.data.remote.model.popular.PopularResponse
import com.example.challengechap7.data.remote.model.popular.Result
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET(EndPoint.Popular.urlPopular)
    suspend fun getPopular(
        @Query("api_key") api: String,
        @Query("page") page: Int = 1
    ): PopularResponse

    @GET(EndPoint.Detail.detail)
    suspend fun getDetail(
        @Path("movieId") movieId: Int,
        @Query("api_key") api: String
    ): Response<Result>

}