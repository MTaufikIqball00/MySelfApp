package com.example.myselfapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myselfapp.data.model.LocationPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FindMeViewModel @Inject constructor() : ViewModel() {

    // Lokasi dummy (misalnya, pusat kota Jakarta)
    private val _myLocation = MutableLiveData<LocationPoint>(LocationPoint(-6.863892, 107.542950)) // Menggunakan LocationPoint
    val myLocation: LiveData<LocationPoint> = _myLocation

    private val _locationName = MutableLiveData<String>("Rumah Saya (Cimahi, Indonesia)")
    val locationName: LiveData<String> = _locationName

    // Fungsi untuk memperbarui lokasi jika diperlukan
    fun updateLocation(latitude: Double, longitude: Double, name: String) {
        _myLocation.value = LocationPoint(latitude, longitude) // Menggunakan LocationPoint
        _locationName.value = name
    }
}