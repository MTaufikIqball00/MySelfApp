package com.example.myselfapp.ui.adapter

import com.example.myselfapp.R
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myselfapp.data.local.entity.GalleryItemEntity
import com.example.myselfapp.databinding.ItemGalleryBinding

class GalleryAdapter(private val galleryItems: List<GalleryItemEntity>) :
    RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>() {

    // ViewHolder untuk setiap item galeri
    inner class GalleryViewHolder(private val binding: ItemGalleryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: GalleryItemEntity) {
            // Muat gambar menggunakan Glide
            Glide.with(binding.ivGalleryImage.context)
                .load(item.imageResId)
                .placeholder(R.drawable.ic_gallery) // Placeholder ikon galeri
                .error(R.drawable.ic_gallery) // Gambar error ikon galeri
                .centerCrop()
                .into(binding.ivGalleryImage)

            // Tampilkan deskripsi jika ada
            if (!item.description.isNullOrBlank()) {
                binding.tvGalleryDescription.text = item.description
                binding.tvGalleryDescription.visibility = ViewGroup.VISIBLE
            } else {
                binding.tvGalleryDescription.visibility = ViewGroup.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        // Mengembang layout item
        val binding = ItemGalleryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GalleryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        // Mengikat data ke ViewHolder pada posisi tertentu
        holder.bind(galleryItems[position])
    }

    override fun getItemCount(): Int {
        // Mengembalikan jumlah total item dalam daftar
        return galleryItems.size
    }
}