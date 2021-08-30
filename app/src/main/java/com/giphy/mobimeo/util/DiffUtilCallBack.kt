package com.giphy.mobimeo.util

import androidx.recyclerview.widget.DiffUtil
import com.giphy.mobimeo.data.model.ImageData

class DiffUtilCallBack : DiffUtil.ItemCallback<ImageData>() {

    override fun areItemsTheSame(oldItem: ImageData, newItem: ImageData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ImageData, newItem: ImageData): Boolean {
        return oldItem == newItem
    }

}