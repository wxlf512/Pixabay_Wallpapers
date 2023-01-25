package dev.wxlf.pixabaywallpapers.presentation.fragments

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.wxlf.pixabaywallpapers.databinding.FragmentCategoriesBinding
import dev.wxlf.pixabaywallpapers.presentation.adapters.CategoriesListAdapter
import dev.wxlf.pixabaywallpapers.presentation.common.Routes

@AndroidEntryPoint
class CategoriesFragment : Fragment() {

    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!

    private val categories = listOf(
        "backgrounds",
        "fashion",
        "nature",
        "science",
        "education",
        "feelings",
        "health",
        "people",
        "religion",
        "places",
        "animals",
        "industry",
        "computer",
        "food",
        "sports",
        "transportation",
        "travel",
        "buildings",
        "business",
        "music"
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.categoriesList.layoutManager = GridLayoutManager(context, 2)
        binding.categoriesList.adapter = CategoriesListAdapter(categories, onCategoryClick = {
            findNavController().navigate(Uri.parse(Routes.Images.route + "?category=$it"))
        })
    }

}