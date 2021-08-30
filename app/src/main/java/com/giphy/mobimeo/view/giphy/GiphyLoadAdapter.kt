package com.giphy.mobimeo.view.giphy

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.giphy.mobimeo.databinding.ItemLoadStateFooterBinding

class GiphyLoadAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<GiphyLoadAdapter.LoadStateViewHolder>() {

    class LoadStateViewHolder(private val binding: ItemLoadStateFooterBinding, retry: () -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.btnRetry.setOnClickListener {
                retry.invoke()
            }
        }

        fun bindState(loadState: LoadState) {

            if (loadState is LoadState.Error) {
                binding.tvErrorMessage.error = loadState.error.localizedMessage
            }
            binding.btnRetry.isVisible = loadState is LoadState.Error
            binding.progressBar.isVisible = loadState is LoadState.Loading
            binding.tvErrorMessage.isVisible = loadState is LoadState.Error

        }

    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bindState(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {

        return LoadStateViewHolder(
            ItemLoadStateFooterBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), retry
        )
    }
}


