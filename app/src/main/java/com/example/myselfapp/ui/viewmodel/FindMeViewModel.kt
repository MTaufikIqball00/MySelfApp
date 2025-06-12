package com.example.myselfapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FindMeViewModel @Inject constructor() : ViewModel() {

    // Lokasi dummy (misalnya, pusat kota Jakarta)
    private val _myLocation = MutableLiveData<LatLng>(LatLng(-6.2088, 106.8456)) // Jakarta
    val myLocation: LiveData<LatLng> = _myLocation

    private val _locationName = MutableLiveData<String>("Rumah Saya (Jakarta, Indonesia)")
    val locationName: LiveData<String> = _locationName

    // Fungsi untuk memperbarui lokasi jika diperlukan
    fun updateLocation(latitude: Double, longitude: Double, name: String) {
        _myLocation.value = LatLng(latitude, longitude)
        _locationName.value = name
    }
}