package dev.wxlf.pixabaywallpapers.presentation.fragments

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.wxlf.pixabaywallpapers.data.entities.ImageEntity
import dev.wxlf.pixabaywallpapers.databinding.FragmentImagesBinding
import dev.wxlf.pixabaywallpapers.presentation.adapters.ImagesListAdapter
import dev.wxlf.pixabaywallpapers.presentation.common.Routes
import dev.wxlf.pixabaywallpapers.presentation.viewmodels.ImagesViewModel
import dev.wxlf.pixabaywallpapers.presentation.viewmodels.utils.ImagesEvent
import dev.wxlf.pixabaywallpapers.presentation.viewmodels.utils.ImagesViewState
import java.util.*

@AndroidEntryPoint
class ImagesFragment : Fragment() {

    private val viewModel by viewModels<ImagesViewModel>()

    private var _binding: FragmentImagesBinding? = null
    private val binding get() = _binding!!

    private val imagesList = arrayListOf<ImageEntity>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImagesBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val category = requireArguments().getString("category").orEmpty()

        viewModel.obtainEvent(ImagesEvent.LoadCategory(category))

        binding.category = category.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString()
        }
        binding.imagesList.layoutManager = GridLayoutManager(context, 2)
        binding.imagesList.adapter =
            ImagesListAdapter(imagesList,
                onImageClick = {
                    findNavController().navigate(Uri.parse(Routes.Image.route + "?id=$it"))
                })

        viewModel.viewState.observe(viewLifecycleOwner) { viewState ->
            when(viewState) {
                is ImagesViewState.CategoryLoaded -> {
                    imagesList.addAll(viewState.images)
                    (binding.imagesList.adapter as ImagesListAdapter).notifyDataSetChanged()
                }
            }
        }
    }

}