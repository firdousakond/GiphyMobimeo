package com.giphy.mobimeo.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.giphy.mobimeo.data.model.ImageData
import com.giphy.mobimeo.data.repository.GiphyRepository
import com.giphy.mobimeo.util.API_KEY
import com.giphy.mobimeo.util.PAGE_SIZE
import java.lang.Exception

class GiphyPagingSource (private val gifRepository: GiphyRepository, private val searchKey: String) : PagingSource<Int, ImageData>() {

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImageData> {
            val pageIndex = params.key ?: PAGE_INDEX
            return try {
                val response = gifRepository.getGifs(apiKey = API_KEY,
                    searchKey = searchKey,
                    offset = pageIndex,
                    pageSize = PAGE_SIZE
                )
                val images  = response.data
                val nextKey =
                    if (response.data?.isEmpty() == true) {
                        null
                    } else {
                        pageIndex.plus(response.pagination?.count!!)
                    }
                LoadResult.Page(
                    data = images.orEmpty(),
                    prevKey = if (pageIndex == PAGE_INDEX) null else pageIndex,
                    nextKey = nextKey
                )

            } catch (exception: Exception) {
                return LoadResult.Error(exception)
            } catch (exception: Exception) {
                return LoadResult.Error(exception)
            }
        }

        /**
         * The refresh key is used for subsequent calls to PagingSource.Load after the initial load.
         */
        override fun getRefreshKey(state: PagingState<Int, ImageData>): Int? {
            // We need to get the previous key (or next key if previous is null) of the page
            // that was closest to the most recently accessed index.
            // Anchor position is the most recently accessed index.
            return state.anchorPosition?.let { anchorPosition ->
                state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                    ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
            }
        }

    companion object{
        private const val PAGE_INDEX = 0
    }

}