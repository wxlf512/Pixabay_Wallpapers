package dev.wxlf.pixabaywallpapers.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import dev.wxlf.pixabaywallpapers.R
import dev.wxlf.pixabaywallpapers.data.entities.ImageEntity
import dev.wxlf.pixabaywallpapers.databinding.ImageItemBinding

class ImagesListAdapter(
    private val imagesUrls: ArrayList<ImageEntity>,
    val onImageClick: (id: Int) -> Unit
) :
    RecyclerView.Adapter<ImagesListAdapter.ImageViewHolder>() {

    inner class ImageViewHolder(private val binding: ImageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(imageEntity: ImageEntity) {
            binding.root.setOnClickListener {
                onImageClick(imageEntity.id)
            }

            binding.imageUrl = imageEntity.webformatURL
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ImageItemBinding>(
            inflater,
            R.layout.image_item,
            parent,
            false
        )
        return ImageViewHolder(binding)
    }

    override fun getItemCount(): Int = imagesUrls.size

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) =
        holder.bind(imagesUrls[position])
}