package com.giphy.mobimeo.data.api

import com.giphy.mobimeo.data.model.GifResponse

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {
    override suspend fun getGifs(
        apiKey: String,
        searchKey: String,
        pageSize: Int,
        offset: Int
    ): GifResponse  = apiService.getGifs(apiKey,searchKey,pageSize,offset)
}