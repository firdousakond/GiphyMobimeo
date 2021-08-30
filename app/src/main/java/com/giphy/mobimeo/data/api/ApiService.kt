package com.giphy.mobimeo.data.api

import com.giphy.mobimeo.data.model.GifResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v1/gifs/search")
    suspend fun getGifs(
        @Query("api_key") apiKey: String,
        @Query("q") searchKey: String,
        @Query("limit") pageSize: Int,
        @Query("offset") offset: Int
    ): GifResponse

}