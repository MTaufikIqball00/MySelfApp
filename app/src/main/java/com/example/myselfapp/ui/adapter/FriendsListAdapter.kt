package com.example.myselfapp.ui.adapter

import com.example.myselfapp.R
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myselfapp.data.local.entity.FriendEntity
import com.example.myselfapp.databinding.ItemFriendBinding

class FriendsListAdapter(private val friends: List<FriendEntity>) :
    RecyclerView.Adapter<FriendsListAdapter.FriendViewHolder>() {

    // ViewHolder untuk setiap item teman
    inner class FriendViewHolder(private val binding: ItemFriendBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(friend: FriendEntity) {
            binding.tvFriendName.text = friend.name
            // Muat gambar menggunakan Glide, dengan bentuk lingkaran
            Glide.with(binding.ivFriendPhoto.context)
                .load(friend.imageUrl)
                .placeholder(R.drawable.ic_default_profile) // Placeholder profil default
                .error(R.drawable.ic_default_profile) // Gambar error profil default
                .circleCrop() // Memotong menjadi lingkaran
                .into(binding.ivFriendPhoto)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        // Mengembang layout item
        val binding = ItemFriendBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FriendViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        // Mengikat data ke ViewHolder pada posisi tertentu
        holder.bind(friends[position])
    }

    override fun getItemCount(): Int {
        // Mengembalikan jumlah total item dalam daftar
        return friends.size
    }
}