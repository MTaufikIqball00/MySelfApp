package com.example.myselfapp.ui.adapter

import android.R
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myselfapp.data.local.entity.DailyActivityEntity
import com.example.myselfapp.databinding.ItemDailyActivityBinding


class DailyActivityAdapter(private val activities: List<DailyActivityEntity>) :
    RecyclerView.Adapter<DailyActivityAdapter.DailyActivityViewHolder>() {

    // ViewHolder untuk setiap item aktivitas
    inner class DailyActivityViewHolder(private val binding: ItemDailyActivityBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(activity: DailyActivityEntity) {
            binding.tvActivityTitle.text = activity.title
            binding.tvActivityDescription.text = activity.description
            // Baris kode untuk memuat gambar menggunakan Glide telah dihapus
            // Jika Anda masih ingin menampilkan placeholder atau gambar default, Anda bisa melakukannya di sini:
            // binding.ivActivityImage.setImageResource(R.drawable.default_image_placeholder) // Contoh
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyActivityViewHolder {
        val binding = ItemDailyActivityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DailyActivityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DailyActivityViewHolder, position: Int) {
        holder.bind(activities[position])
    }

    override fun getItemCount(): Int {
        return activities.size
    }
}