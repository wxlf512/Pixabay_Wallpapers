package dev.wxlf.pixabaywallpapers.data.entities

import com.google.gson.annotations.SerializedName


data class CategoryEntity(

    @SerializedName("total") var total: Int,
    @SerializedName("totalHits") var totalHits: Int,
    @SerializedName("hits") var hits: ArrayList<ImageEntity>

)