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
import dev.wxlf.pixabaywallpapers.databinding.FragmentCategoriesBinding
import dev.wxlf.pixabaywallpapers.presentation.adapters.CategoriesListAdapter
import dev.wxlf.pixabaywallpapers.presentation.common.Routes
import dev.wxlf.pixabaywallpapers.presentation.viewmodels.CategoriesViewModel
import dev.wxlf.pixabaywallpapers.presentation.viewmodels.utils.CategoriesEvent
import dev.wxlf.pixabaywallpapers.presentation.viewmodels.utils.CategoriesViewState

@AndroidEntryPoint
class CategoriesFragment : Fragment() {

    private val viewModel by viewModels<CategoriesViewModel>()

    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.obtainEvent(CategoriesEvent.LoadCategories)

        viewModel.viewState.observe(viewLifecycleOwner) { viewState ->
            when (viewState) {
                CategoriesViewState.LoadingCategories -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.errorText.visibility = View.GONE
                    binding.categoriesList.visibility = View.GONE
                }
                is CategoriesViewState.CategoriesLoaded -> {
                    binding.progressBar.visibility = View.GONE
                    binding.errorText.visibility = View.GONE
                    binding.categoriesList.visibility = View.VISIBLE
                    binding.categoriesList.layoutManager = GridLayoutManager(context, 2)
                    binding.categoriesList.adapter = CategoriesListAdapter(viewState.categories, onCategoryClick = {
                        findNavController().navigate(Uri.parse(Routes.Images.route + "?category=$it"))
                    })
                    (binding.categoriesList.adapter as CategoriesListAdapter).notifyDataSetChanged()
                }
                is CategoriesViewState.ErrorState -> {
                    binding.progressBar.visibility = View.GONE
                    binding.errorText.visibility = View.VISIBLE
                    binding.categoriesList.visibility = View.GONE
                    binding.errorText.text = viewState.message
                }
            }
        }
    }

}