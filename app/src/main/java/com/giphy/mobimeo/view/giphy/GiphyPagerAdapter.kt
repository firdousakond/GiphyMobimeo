package com.giphy.mobimeo.view.giphy

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.giphy.mobimeo.data.model.ImageData
import com.giphy.mobimeo.databinding.ItemGifBinding
import com.giphy.mobimeo.util.DiffUtilCallBack

class GiphyPagerAdapter(private val onGifItemClickListener: GifItemClickListener) :
    PagingDataAdapter<ImageData, GiphyPagerAdapter.GiphyViewHolder>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GiphyViewHolder {

        return GiphyViewHolder(
            ItemGifBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: GiphyViewHolder, position: Int) {
        getItem(position)?.let { holder.bindPost(it) }
    }

    inner class GiphyViewHolder(private val binding: ItemGifBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindPost(imageData: ImageData) {
            binding.root.setOnClickListener {
                onGifItemClickListener.onGifItemClick(imageData)
            }
            val circularProgressDrawable = CircularProgressDrawable(binding.root.context)
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()

            Glide.with(binding.root.context)
                .load(imageData.images?.original?.url)
                .transition(DrawableTransitionOptions.withCrossFade(1000))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(circularProgressDrawable)
                .into(binding.ivImage)
        }
    }

    interface GifItemClickListener {
        fun onGifItemClick(imageData: ImageData)
    }
}