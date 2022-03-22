package com.example.repidapi.rapidImage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.repidapi.R

class AdapterImage : RecyclerView.Adapter<AdapterImage.ImageViewHolder>() {

    private val urls: MutableList<String> = mutableListOf()

    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_image, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        Glide
            .with(holder.itemView.context)
            .load(urls[position])
            .into(holder.image)

    }

    override fun getItemCount(): Int {
        return urls.size
    }

    fun setAll(list: List<String>) {
        urls.clear()
        urls.addAll(list)
        notifyDataSetChanged()
    }
}