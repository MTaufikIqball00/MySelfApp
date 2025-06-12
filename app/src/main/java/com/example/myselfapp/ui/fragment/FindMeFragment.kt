package com.example.myselfapp.ui.fragment

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions
import com.example.myselfapp.R
import com.example.myselfapp.databinding.FragmentFindMeBinding
import com.example.myselfapp.ui.viewmodel.FindMeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FindMeFragment : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentFindMeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FindMeViewModel by viewModels()

    private var googleMap: GoogleMap? = null

    // Register ActivityResultLauncher untuk meminta izin lokasi
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                // Izin lokasi presisi diberikan.
                enableMyLocation()
            }
            permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                // Hanya izin lokasi perkiraan diberikan.
                enableMyLocation()
                Toast.makeText(context, "Izin lokasi presisi tidak diberikan, akurasi mungkin berkurang.", Toast.LENGTH_LONG).show()
            }
            else -> {
                // Tidak ada izin lokasi diberikan.
                Toast.makeText(context, "Izin lokasi diperlukan untuk menampilkan peta.", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFindMeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Dapatkan SupportMapFragment dan siapkan callback peta
        val mapFragment = childFragmentManager.findFragmentById(R.id.map_view) as SupportMapFragment?
        mapFragment?.getMapAsync(this)

        // Amati LiveData dari ViewModel
        viewModel.myLocation.observe(viewLifecycleOwner) { latLng ->
            latLng?.let {
                googleMap?.clear() // Hapus marker sebelumnya
                googleMap?.addMarker(MarkerOptions().position(it).title(viewModel.locationName.value))
                googleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(it, 15f)) // Zoom ke lokasi
            }
        }

        viewModel.locationName.observe(viewLifecycleOwner) { name ->
            binding.tvLocationName.text = name
        }
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        checkLocationPermissions()
        // Set UI Settings (opsional)
        googleMap?.uiSettings?.isZoomControlsEnabled = true
        googleMap?.uiSettings?.isCompassEnabled = true

        // Tambahkan marker awal dari ViewModel
        viewModel.myLocation.value?.let { latLng ->
            googleMap?.addMarker(MarkerOptions().position(latLng).title(viewModel.locationName.value))
            googleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
        }
    }

    private fun checkLocationPermissions() {
        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                // Izin sudah diberikan
                enableMyLocation()
            }
            shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) -> {
                // Berikan penjelasan mengapa izin diperlukan
                Toast.makeText(context, "Aplikasi ini memerlukan izin lokasi untuk menampilkan lokasi Anda di peta.", Toast.LENGTH_LONG).show()
                requestPermissionLauncher.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                )
            }
            else -> {
                // Minta izin
                requestPermissionLauncher.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                )
            }
        }
    }

    private fun enableMyLocation() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            googleMap?.isMyLocationEnabled = true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        googleMap = null // Hindari memory leak
    }
}