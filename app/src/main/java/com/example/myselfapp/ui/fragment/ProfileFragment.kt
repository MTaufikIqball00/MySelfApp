package com.example.myselfapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myselfapp.R
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.myselfapp.databinding.FragmentProfileBinding // Pastikan binding ini ada
import com.example.myselfapp.ui.viewmodel.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Observe LiveData dari ViewModel
        viewModel.userProfile.observe(viewLifecycleOwner) { profile ->
            if (profile != null) {
                with(binding) {
                    tvProfileDetailName.text = profile.name
                    tvProfileDetailDescription.text = profile.description
                    tvProfileDetailHobbies.text = "Hobi: ${profile.hobbies.joinToString(", ")}"
                    tvProfileDetailMakes.text = "Membuat: ${profile.makes.joinToString(", ")}"
                    tvProfileDetailInterests.text = "Minat: ${profile.interests.joinToString(", ")}"
                    tvProfileDetailDreams.text = "Cita-cita: ${profile.dreams.joinToString(", ")}"

                    if (isAdded && !isDetached) {
                        Glide.with(this@ProfileFragment)
                            .load(profile.photoResId)
                            .placeholder(R.drawable.ic_default_profile)
                            .error(R.drawable.ic_default_profile)
                            .circleCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(ivProfileDetailPhoto)
                    }

                }
            } else {
                // Tampilkan pesan atau UI default jika profil tidak tersedia
                binding.tvProfileDetailName.text = getString(R.string.name_not_available)
                binding.tvProfileDetailDescription.text = getString(R.string.description_not_available)
                binding.ivProfileDetailPhoto.setImageResource(R.drawable.ic_default_profile)
                binding.tvProfileDetailHobbies.text = ""
                binding.tvProfileDetailMakes.text = ""
                binding.tvProfileDetailInterests.text = ""
                binding.tvProfileDetailDreams.text = ""
            }
        }

        // Muat data profil
        viewModel.loadUserProfileDetail()

        // Setup listener untuk tombol About
        binding.btnAboutApp.setOnClickListener {
            val dialogFragment = AboutDialogFragment()
            dialogFragment.show(childFragmentManager, "AboutDialogFragment")
        }

        // Setup listener untuk tombol Kontak Saya
        binding.btnContactMe.setOnClickListener {
            findNavController().navigate(R.id.contactFragment) // Mengarahkan ke ContactFragment
        }

        // Setup listener untuk tombol Temukan Saya di Peta (BARU)
//        binding.btnFindMe.setOnClickListener {
//            findNavController().navigate(R.id.findMeFragment) // Mengarahkan ke FindMeFragment
//        }
    }

    // Panggil ulang loadUserProfileDetail() saat dialog ditutup
    override fun onResume() {
        super.onResume()
        viewModel.loadUserProfileDetail()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}