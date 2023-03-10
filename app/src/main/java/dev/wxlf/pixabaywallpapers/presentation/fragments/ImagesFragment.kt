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
import androidx.recyclerview.widget.RecyclerView
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
        binding.imagesList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = binding.imagesList.layoutManager as GridLayoutManager
                if (layoutManager.findLastVisibleItemPosition() >= imagesList.lastIndex - 4) {
                    viewModel.obtainEvent(ImagesEvent.LoadMoreImages)
                }
            }
        })

        viewModel.viewState.observe(viewLifecycleOwner) { viewState ->
            when(viewState) {
                ImagesViewState.LoadingCategory -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.errorText.visibility = View.GONE
                    binding.imagesList.visibility = View.GONE
                }
                is ImagesViewState.CategoryLoaded -> {
                    binding.progressBar.visibility = View.GONE
                    binding.errorText.visibility = View.GONE
                    binding.imagesList.visibility = View.VISIBLE
                    imagesList.addAll(viewState.images)
                    (binding.imagesList.adapter as ImagesListAdapter).notifyDataSetChanged()
                }
                is ImagesViewState.ErrorState -> {
                    binding.progressBar.visibility = View.GONE
                    binding.errorText.visibility = View.VISIBLE
                    binding.imagesList.visibility = View.GONE
                    binding.errorText.text = viewState.message
                }
            }
        }
    }

}