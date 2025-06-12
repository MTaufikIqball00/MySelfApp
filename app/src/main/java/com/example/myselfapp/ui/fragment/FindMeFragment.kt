package com.example.myselfapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.myselfapp.ui.customview.SimpleMapView
import com.example.myselfapp.databinding.FragmentFindMeBinding
import com.example.myselfapp.ui.viewmodel.FindMeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FindMeFragment : Fragment() { // Tidak lagi mengimplementasikan OnMapReadyCallback

    private var _binding: FragmentFindMeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FindMeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFindMeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TIDAK PERLU lagi mendapatkan SupportMapFragment
        // TIDAK PERLU lagi checkLocationPermissions() atau enableMyLocation()

        // Amati LiveData dari ViewModel
        viewModel.myLocation.observe(viewLifecycleOwner) { locationPoint ->
            locationPoint?.let {
                // Panggil fungsi setLocation pada SimpleMapView kustom
                binding.simpleMapView.setLocation(it, viewModel.locationName.value ?: "")
            }
        }

        viewModel.locationName.observe(viewLifecycleOwner) { name ->
            binding.tvLocationName.text = name
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
