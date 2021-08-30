package com.giphy.mobimeo.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ImageData(
    val analytics_response_payload: String? = null,
    val bitly_gif_url: String? = null,
    val bitly_url: String? = null,
    val content_url: String? = null,
    val embed_url: String? = null,
    val id: String? = null,
    val images: Images? = null,
    val import_datetime: String? = null,
    val is_sticker: Int? = null,
    val rating: String? = null,
    val slug: String? = null,
    val source: String? = null,
    val source_post_url: String? = null,
    val source_tld: String? = null,
    val title: String? = null,
    val trending_datetime: String? = null,
    val type: String? = null,
    val url: String? = null,
    val user: User? = null,
    val username: String? = null
): Parcelable