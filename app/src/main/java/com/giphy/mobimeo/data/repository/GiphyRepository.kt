package com.giphy.mobimeo.data.repository

import com.giphy.mobimeo.data.api.ApiHelper

class GiphyRepository(private val apiHelper: ApiHelper) {

    suspend fun getGifs(
        apiKey: String,
        searchKey: String,
        pageSize: Int,
        offset: Int
    ) = apiHelper.getGifs(apiKey, searchKey, pageSize, offset)
}