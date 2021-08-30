package com.giphy.mobimeo.view.giphy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.giphy.mobimeo.R
import com.giphy.mobimeo.databinding.FragmentGifBinding
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.giphy.mobimeo.data.model.ImageData
import com.giphy.mobimeo.util.NetworkUtil
import com.giphy.mobimeo.util.hideKeyboard
import com.giphy.mobimeo.viewmodel.GiphyViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber


class GiphyFragment : Fragment(), GiphyPagerAdapter.GifItemClickListener {

    private lateinit var binding: FragmentGifBinding
    private val viewModel: GiphyViewModel by viewModel()
    private lateinit var giphyAdapter: GiphyPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        giphyAdapter = GiphyPagerAdapter(this)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_gif, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeList()
        observePageLoadState()
        setListeners()
    }

    private fun observePageLoadState() {
        lifecycleScope.launch {
            giphyAdapter.loadStateFlow.collectLatest { loadStates ->
                binding.progressBar.isVisible = loadStates.refresh is LoadState.Loading
            }
        }
    }

    private fun initializeList() {
        binding.etSearch.requestFocus()
        val gridLayoutManager = GridLayoutManager(requireContext(),2)
        binding.rvGif.apply {
            layoutManager = gridLayoutManager
            adapter =  giphyAdapter.withLoadStateFooter(
                footer = GiphyLoadAdapter(giphyAdapter::retry)
            )
        }
    }

    private fun setListeners() {

        binding.etSearch.setOnEditorActionListener(OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                performSearch()
                return@OnEditorActionListener true
            }
            false
        })
    }

    private fun performSearch() {
        if (NetworkUtil.isNetworkConnected(requireContext())) {
            if (!binding.etSearch.text.isNullOrEmpty())
                binding.etSearch.hideKeyboard()
                lifecycleScope.launch {
                    viewModel.getGifs(searchKey = binding.etSearch.text.toString())
                        .collectLatest { giphyData ->
                            Timber.d("$TAG: $giphyData")
                            giphyAdapter.submitData(giphyData)
                        }
                }

        }
    }

    override fun onGifItemClick(imageData: ImageData) {
        val bundle = Bundle()
        bundle.putParcelable(GiphyDetailsFragment.IMAGE_DATA,imageData)
        findNavController().navigate(R.id.gifDetailsFragment,bundle)
    }

    companion object{
        private val TAG = GiphyFragment::class.java.simpleName
    }
}