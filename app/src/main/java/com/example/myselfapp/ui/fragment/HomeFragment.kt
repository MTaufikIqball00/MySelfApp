package com.example.myselfapp.ui.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.myselfapp.R
import com.example.myselfapp.databinding.FragmentHomeBinding
import com.example.myselfapp.ui.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Observe LiveData from ViewModel
        viewModel.userProfile.observe(viewLifecycleOwner) { profile ->
            try {
                if (profile != null) {
                    with(binding) {
                        tvProfileName.text = profile.name.takeIf { it.isNotBlank() } ?: getString(R.string.name_not_available)
                        tvProfileDescription.text = profile.description.takeIf { it.isNotBlank() } ?: getString(R.string.description_not_available)

                        if (isAdded && !isDetached) {
                            Glide.with(this@HomeFragment)
                                .load(profile.photoResId)
                                .placeholder(R.drawable.ic_default_profile)
                                .error(R.drawable.ic_default_profile)
                                .circleCrop()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(ivProfilePhoto)
                        }
                    }
                } else {
                    // Handle null profile case
                    resetProfileUI()
                }
            } catch (e: Exception) {
                Log.e("HomeFragment", "Error updating profile UI", e)
                // Handle error gracefully
                android.widget.Toast.makeText(context, "Gagal memuat data profil", android.widget.Toast.LENGTH_SHORT).show()
                resetProfileUI() // Reset UI jika terjadi kesalahan
            }
        }


        viewModel.interests.observe(viewLifecycleOwner) { Minat ->
            binding.tvInterests.text = "Minat: " + Minat.joinToString(", ").takeIf { it.isNotBlank() } ?: getString(R.string.interests_not_available)
        }

        viewModel.hobbies.observe(viewLifecycleOwner) { hobbies ->
            binding.tvHobbies.text = "Hobi: " + hobbies.joinToString(", ").takeIf { it.isNotBlank() } ?: getString(R.string.hobbies_not_available)
        }

        viewModel.makes.observe(viewLifecycleOwner) { makes ->
            binding.tvMakes.text = "Membuat: " + makes.joinToString(", ").takeIf { it.isNotBlank() } ?: getString(R.string.makes_not_available)
        }

        viewModel.dreams.observe(viewLifecycleOwner) { dreams ->
            binding.tvDreams.text = "Cita-cita: " + dreams.joinToString(", ").takeIf { it.isNotBlank() } ?: getString(R.string.dreams_not_available)
        }

        // --- Navigasi ke GalleryFragment ketika tombol "Lihat Galeri" diklik ---
        binding.btnViewGallery.setOnClickListener {
            findNavController().navigate(R.id.galleryFragment) // Mengarahkan ke ID fragment di nav_graph.xml
        }
        // --- Akhir Navigasi ---


        // Trigger data loading
        viewModel.loadUserProfile()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     * Fungsi untuk mengatur ulang elemen UI profil ke nilai default
     * atau menyembunyikannya jika data profil tidak tersedia.
     */
    private fun resetProfileUI() {
        with(binding) {
            tvProfileName.text = getString(R.string.name_not_available)
            tvProfileDescription.text = getString(R.string.description_not_available)
            ivProfilePhoto.setImageResource(R.drawable.ic_default_profile) // Atur ke gambar default

            tvInterests.text = getString(R.string.interests_not_available)
            tvHobbies.text = getString(R.string.hobbies_not_available)
            tvMakes.text = getString(R.string.makes_not_available)
            tvDreams.text = getString(R.string.dreams_not_available)

            // Anda bisa menyembunyikan CardView atau elemen lain jika perlu
            // binding.cardProfile.visibility = View.GONE
            // binding.cardDetails.visibility = View.GONE
        }
    }
}
