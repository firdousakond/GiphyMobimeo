package com.giphy.mobimeo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.giphy.mobimeo.data.model.ImageData
import com.giphy.mobimeo.data.paging.GiphyPagingSource
import com.giphy.mobimeo.data.repository.GiphyRepository
import com.giphy.mobimeo.util.PAGE_SIZE
import kotlinx.coroutines.flow.Flow

class GiphyViewModel(private val gifRepository: GiphyRepository) : ViewModel() {

    fun getGifs(searchKey: String): Flow<PagingData<ImageData>> {
        return Pager(
            PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = true)
        ) {
            GiphyPagingSource(gifRepository,searchKey)
        }.flow.cachedIn(viewModelScope)
    }
}