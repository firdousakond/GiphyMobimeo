package com.giphy.mobimeo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingSource
import com.giphy.mobimeo.data.model.*
import com.giphy.mobimeo.data.paging.GiphyPagingSource
import com.giphy.mobimeo.data.repository.GiphyRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.*
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.lang.RuntimeException

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GiphyPagingSourceTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Mock lateinit var giphyRepository: GiphyRepository

    private lateinit var giphyPagingSource: GiphyPagingSource

    @Before
    fun setUp() {
        giphyPagingSource = GiphyPagingSource(giphyRepository, "")
    }

    @Test
    fun `giphy paging source - http error response test`(){
        runBlockingTest {
            val error = RuntimeException("404", Throwable())
            given(giphyRepository.getGifs(anyString(), anyString(), anyInt(), anyInt())).willThrow(error)
            val expectedResult = PagingSource.LoadResult.Error<Int,ImageData>(error)
            assertEquals(
                expectedResult,
                 giphyPagingSource.load(
                     PagingSource.LoadParams.Refresh(
                         key = 0,
                         loadSize = 1,
                         placeholdersEnabled = false
                     )
                 )
                )
        }
    }

    @Test
    fun `giphy paging source load - failure - received null`() = runBlockingTest {
        given(giphyRepository.getGifs(anyString(), anyString(), anyInt(), anyInt())).willReturn(null)
        val expectedResult = PagingSource.LoadResult.Error<Int, ImageData>(NullPointerException())
        assertEquals(
            expectedResult.toString(), giphyPagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = 0,
                    loadSize = 1,
                    placeholdersEnabled = false
                )
            ).toString()
        )
    }

    @Test
    fun `giphy paging source refresh - success`() = runBlockingTest {
        given(giphyRepository.getGifs(anyString(), anyString(), anyInt(), anyInt())).willReturn(
            giphyResponse)

        val expectedResult = PagingSource.LoadResult.Page(
            data = giphyResponse.data.orEmpty(),
            prevKey = null,
            nextKey = 3
        )
        assertEquals(
            expectedResult, giphyPagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = 0,
                    loadSize = 3,
                    placeholdersEnabled = false
                )
            )
        )
    }

    @Test
    fun `giphy paging source append - success`() = runBlockingTest {
        given(giphyRepository.getGifs(anyString(), anyString(), anyInt(), anyInt())).willReturn(
            nextGiphyResponse)
        val expectedResult = PagingSource.LoadResult.Page(
            data = nextGiphyResponse.data.orEmpty(),
            prevKey = 1,
            nextKey = 4
        )
        assertEquals(
            expectedResult, giphyPagingSource.load(
                PagingSource.LoadParams.Append(
                    key = 1,
                    loadSize = 3,
                    placeholdersEnabled = false
                )
            )
        )
    }

    @Test
    fun `giphy paging source prepend - success`() = runBlockingTest {
        given(giphyRepository.getGifs(anyString(), anyString(), anyInt(), anyInt())).willReturn(
            giphyResponse)
        val expectedResult = PagingSource.LoadResult.Page(
            data = giphyResponse.data.orEmpty(),
            prevKey = null,
            nextKey = 3
        )
        assertEquals(
            expectedResult, giphyPagingSource.load(
                PagingSource.LoadParams.Prepend(
                    key = 0,
                    loadSize = 3,
                    placeholdersEnabled = false
                )
            )
        )
    }

    companion object {
        val giphyResponse = GifResponse(
            data = listOf(
                ImageData(id = "id1", images = Images(original = Original(url = "testUrl1"))),
                ImageData(id = "id2", images = Images(original = Original(url = "testUrl2"))),
                ImageData(id = "id3", images = Images(original = Original(url = "testUrl3")))
            ),
            pagination = Pagination(count = 3, offset = 0)
        )

        val nextGiphyResponse = GifResponse(
            data = listOf(
                ImageData(id = "id1", images = Images(original = Original(url = "testUrl1"))),
                ImageData(id = "id2", images = Images(original = Original(url = "testUrl2"))),
                ImageData(id = "id3", images = Images(original = Original(url = "testUrl3")))
            ),
            pagination = Pagination(count = 3, offset = 3)
        )
    }

}