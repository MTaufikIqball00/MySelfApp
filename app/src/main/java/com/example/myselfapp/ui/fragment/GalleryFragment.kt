package com.example.myselfapp.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myselfapp.databinding.FragmentGalleryBinding // Pastikan binding ini ada
import com.example.myselfapp.ui.adapter.GalleryAdapter
import com.example.myselfapp.ui.viewmodel.GalleryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!
    private val viewModel: GalleryViewModel by viewModels()

    private lateinit var galleryAdapter: GalleryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeViewModel()

        viewModel.loadGalleryItems()
    }

    private fun setupRecyclerView() {
        galleryAdapter = GalleryAdapter(emptyList())
        binding.rvGallery.apply {
            layoutManager = GridLayoutManager(context, 3) // Grid 3 kolom
            adapter = galleryAdapter
        }
    }

    private fun observeViewModel() {
        viewModel.galleryItems.observe(viewLifecycleOwner) { items ->
            items?.let {
                galleryAdapter = GalleryAdapter(it)
                binding.rvGallery.adapter = galleryAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}