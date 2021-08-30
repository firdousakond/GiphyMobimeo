package com.giphy.mobimeo.view.giphy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.giphy.mobimeo.R
import com.giphy.mobimeo.data.model.ImageData
import com.giphy.mobimeo.databinding.FragmentGifDetailsBinding
import com.google.android.material.transition.MaterialContainerTransform

class GiphyDetailsFragment : Fragment() {

    private lateinit var binding: FragmentGifDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val imageData: ImageData? = it.getParcelable(IMAGE_DATA)
            setUIData(imageData)
        }
        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_gif_details, container, false)
        return binding.root
    }

    private fun setUIData(imageData: ImageData?) {
        val circularProgressDrawable = CircularProgressDrawable(requireContext())
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        Glide.with(requireContext())
            .load(imageData?.images?.original?.url)
            .transition(DrawableTransitionOptions.withCrossFade(1000))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(circularProgressDrawable)
            .into(binding.ivImage)
        binding.tvName.text = imageData?.title
    }

    companion object {
        const val IMAGE_DATA = "imageData"
    }
}