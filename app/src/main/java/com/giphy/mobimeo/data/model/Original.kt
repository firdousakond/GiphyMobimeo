package com.giphy.mobimeo.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Original(
    val frames: String? = null,
    val hash: String? = null,
    val height: String? = null,
    val mp4: String? = null,
    val mp4_size: String? = null,
    val size: String? = null,
    val url: String? = null,
    val webp: String? = null,
    val webp_size: String? = null,
    val width: String? = null
):Parcelable