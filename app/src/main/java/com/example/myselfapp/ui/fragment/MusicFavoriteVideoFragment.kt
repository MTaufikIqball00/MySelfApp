package com.example.myselfapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myselfapp.databinding.FragmentMusicFavoriteVideoBinding
import com.example.myselfapp.data.local.entity.MusicVideoEntity
import com.example.myselfapp.ui.adapter.MusicVideoAdapter
import com.example.myselfapp.ui.viewmodel.MusicVideoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MusicFavoriteVideoFragment : Fragment(), MusicVideoAdapter.OnItemClickListener {

    private var _binding: FragmentMusicFavoriteVideoBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MusicVideoViewModel by viewModels()

    private lateinit var musicVideoAdapter: MusicVideoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMusicFavoriteVideoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeViewModel()

        viewModel.loadMusicVideos()
    }

    private fun setupRecyclerView() {
        musicVideoAdapter = MusicVideoAdapter(emptyList())
        musicVideoAdapter.setOnItemClickListener(this) // Set listener
        binding.rvMusicVideos.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = musicVideoAdapter
        }
    }

    private fun observeViewModel() {
        viewModel.musicVideos.observe(viewLifecycleOwner) { videos ->
            videos?.let {
                musicVideoAdapter = MusicVideoAdapter(it)
                musicVideoAdapter.setOnItemClickListener(this) // Set listener lagi setelah update adapter
                binding.rvMusicVideos.adapter = musicVideoAdapter
            }
        }
    }

    override fun onItemClick(musicVideo: MusicVideoEntity) {
        // Tangani klik item di sini, misalnya membuka link di browser
        // Log.d("MusicVideoFragment", "Item clicked: ${musicVideo.title} - ${musicVideo.contentUrl}")
        // Kode untuk membuka URL sudah ada di adapter sebagai fallback,
        // tapi Anda bisa menambahkan logika kustom di sini jika diperlukan.
        // Contoh: Navigasi ke detail video kustom
        // findNavController().navigate(R.id.action_to_videoDetailFragment, bundleOf("url" to musicVideo.contentUrl))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}