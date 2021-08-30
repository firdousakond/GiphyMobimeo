package com.giphy.mobimeo.data.api

import com.giphy.mobimeo.data.model.GifResponse

interface ApiHelper {

    suspend fun getGifs(
        apiKey: String,
        searchKey: String,
        pageSize: Int,
        offset: Int
    ): GifResponse
}