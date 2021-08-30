package com.giphy.mobimeo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import com.giphy.mobimeo.data.model.ImageData
import com.giphy.mobimeo.data.repository.GiphyRepository
import com.giphy.mobimeo.viewmodel.GiphyViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GiphyViewModelTest {

    @Mock
    lateinit var giphyRepository: GiphyRepository
    private lateinit var giphyViewModel: GiphyViewModel

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Before
    fun setup() {
        giphyViewModel = GiphyViewModel(giphyRepository)
    }

    @Test
    fun `giphy data flow - success return`() {
        runBlockingTest {
            val flow: Flow<PagingData<ImageData>> = giphyViewModel.getGifs("test")
            assertNotNull(flow)
        }
    }
}