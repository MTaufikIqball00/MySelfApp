package com.example.myselfapp.ui.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myselfapp.R  // ✅ Import yang benar
import com.example.myselfapp.data.local.entity.MusicVideoEntity
import com.example.myselfapp.databinding.ItemMusicVideoBinding

class MusicVideoAdapter(private val musicVideos: List<MusicVideoEntity>) :
    RecyclerView.Adapter<MusicVideoAdapter.MusicVideoViewHolder>() {

    // Interface untuk menangani klik item
    interface OnItemClickListener {
        fun onItemClick(musicVideo: MusicVideoEntity)
    }

    // Listener bisa diatur dari Fragment/Activity
    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    // ViewHolder untuk setiap item musik/video
    inner class MusicVideoViewHolder(private val binding: ItemMusicVideoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(musicVideo: MusicVideoEntity) {
            binding.tvMusicVideoTitle.text = musicVideo.title
            binding.tvMusicVideoArtist.text = musicVideo.artist ?: "Artis Tidak Diketahui"
            // Menggunakan musicVideo.type dan mengkapitalisasi huruf pertama
            binding.tvContentType.text = musicVideo.type.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }


            // Muat thumbnail menggunakan Glide dari 'thumbnailUrl'
            Glide.with(binding.ivMusicVideoThumbnail.context)
                .load(musicVideo.thumbnailUrl) // Menggunakan thumbnailUrl yang baru
                .placeholder(R.drawable.ic_music_video) // Menggunakan drawable bawaan Android
                .error(R.drawable.ic_music_video) // Menggunakan drawable bawaan Android
                .centerCrop()
                .into(binding.ivMusicVideoThumbnail)

            // Atur click listener untuk membuka konten (musicVideo.contentUrl)
            binding.root.setOnClickListener {
                listener?.onItemClick(musicVideo) ?: run {
                    // Default action: open contentUrl in browser or with appropriate app
                    try {
                        val intent = Intent(Intent.ACTION_VIEW)
                        // Gunakan contentUrl untuk Intent
                        intent.data = Uri.parse(musicVideo.contentUrl)

                        // Untuk video YouTube, ACTION_VIEW dengan URI langsung biasanya akan membuka YouTube app
                        // Untuk file media lokal, Anda mungkin perlu setDataAndType
                        // Jika mimeType tersedia dan tidak kosong, gunakan itu
                        if (!musicVideo.mimeType.isNullOrBlank()) {
                            intent.setDataAndType(Uri.parse(musicVideo.contentUrl), musicVideo.mimeType)
                        } else {
                            // Coba infer type jika mimeType kosong
                            when (musicVideo.type.lowercase()) {
                                "music" -> intent.setDataAndType(Uri.parse(musicVideo.contentUrl), "audio/*")
                                "video" -> intent.setDataAndType(Uri.parse(musicVideo.contentUrl), "video/*")
                            }
                        }


                        // FLAG_GRANT_READ_URI_PERMISSION hanya diperlukan jika URI menunjuk ke file lokal yang memerlukan izin baca
                        // Untuk URL web seperti YouTube, ini tidak diperlukan dan bahkan bisa menyebabkan masalah jika tidak ada content provider.
                        // Saya akan menghapusnya untuk URL web. Jika Anda benar-benar memutar file lokal, Anda mungkin perlu menyesuaikannya.
                        // intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

                        binding.root.context.startActivity(intent)
                    } catch (e: Exception) {
                        android.widget.Toast.makeText(
                            binding.root.context,
                            "Tidak dapat membuka konten: ${e.message}",
                            android.widget.Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicVideoViewHolder {
        val binding = ItemMusicVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MusicVideoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MusicVideoViewHolder, position: Int) {
        holder.bind(musicVideos[position])
    }

    override fun getItemCount(): Int {
        return musicVideos.size
    }
}