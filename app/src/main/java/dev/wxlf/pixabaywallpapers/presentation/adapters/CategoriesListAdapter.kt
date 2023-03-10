package dev.wxlf.pixabaywallpapers.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import dev.wxlf.pixabaywallpapers.R
import dev.wxlf.pixabaywallpapers.databinding.CategoryItemBinding
import java.util.*

class CategoriesListAdapter(
    private val categories: List<Pair<String, String>>,
    val onCategoryClick: (category: String) -> Unit
) :
    RecyclerView.Adapter<CategoriesListAdapter.CategoryViewHolder>() {

    inner class CategoryViewHolder(private val binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(category: Pair<String, String>) {
            binding.root.setOnClickListener {
                onCategoryClick(category.first)
            }

            binding.category =
                Pair(category.first.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }, category.second)
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<CategoryItemBinding>(
            inflater,
            R.layout.category_item,
            parent,
            false
        )
        return CategoryViewHolder(binding)
    }

    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) =
        holder.bind(categories[position])
}