package com.giphy.mobimeo.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Images(
    val original: Original? = null,
):Parcelable