package com.giphy.mobimeo.data.model

data class GifResponse(
    val data: List<ImageData>?,
    val meta: Meta? = null,
    val pagination: Pagination?
)