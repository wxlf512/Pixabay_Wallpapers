package dev.wxlf.pixabaywallpapers.presentation.fragments

import android.app.WallpaperManager
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import dagger.hilt.android.AndroidEntryPoint
import dev.wxlf.pixabaywallpapers.R
import dev.wxlf.pixabaywallpapers.databinding.FragmentImageBinding
import dev.wxlf.pixabaywallpapers.presentation.viewmodels.ImageViewModel
import dev.wxlf.pixabaywallpapers.presentation.viewmodels.utils.ImageEvent
import dev.wxlf.pixabaywallpapers.presentation.viewmodels.utils.ImageViewState

@AndroidEntryPoint
class ImageFragment : Fragment() {

    private val viewModel by viewModels<ImageViewModel>()

    private var _binding: FragmentImageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.obtainEvent(ImageEvent.LoadImage(requireArguments().getInt("id")))

        viewModel.viewState.observe(viewLifecycleOwner) { viewState ->
            when (viewState) {
                ImageViewState.LoadingImage -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.errorText.visibility = View.GONE
                    binding.imageView.visibility = View.GONE
                    binding.setAsWallpaperButton.visibility = View.GONE
                }
                is ImageViewState.ImageLoaded -> {
                    binding.progressBar.visibility = View.GONE
                    binding.errorText.visibility = View.GONE
                    binding.imageView.visibility = View.VISIBLE
                    binding.setAsWallpaperButton.visibility = View.VISIBLE
                    binding.imageUrl = viewState.imageUrl
                    binding.setAsWallpaperButton.setOnClickListener {
                        val wallpaperManager = WallpaperManager.getInstance(context)
                        Glide.with(this)
                            .asBitmap()
                            .load(viewState.imageUrl)
                            .into(object : CustomTarget<Bitmap>(){
                                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                                    wallpaperManager.setBitmap(resource)
                                    Toast.makeText(context, getString(R.string.wallpapers_installed), Toast.LENGTH_LONG).show()
                                }

                                override fun onLoadCleared(placeholder: Drawable?) {}
                            })
                    }
                }
                is ImageViewState.ErrorState -> {
                    binding.progressBar.visibility = View.GONE
                    binding.errorText.visibility = View.VISIBLE
                    binding.imageView.visibility = View.GONE
                    binding.setAsWallpaperButton.visibility = View.GONE
                    binding.errorText.text = viewState.message
                }
            }
        }
    }

}