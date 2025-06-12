package com.example.myselfapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor() : ViewModel() {


        // Data kontak dummy (bisa diatur dari resource string atau hardcode)
        private val _phoneNumber = MutableLiveData<String>("081234567890")
        val phoneNumber: LiveData<String> = _phoneNumber

        private val _emailAddress = MutableLiveData<String>("taufik.10122336@mahasiswa.unikom.ac.id")
        val emailAddress: LiveData<String> = _emailAddress

        private val _socialMediaLinks = MutableLiveData<Map<String, String>>(
            mapOf(
                "Instagram" to "https://www.instagram.com/taufikiqball_/?utm_source=ig_web_button_share_sheet", // URL DARI SINI
                "LinkedIn" to "https://www.linkedin.com/in/taufik-iqballl-55805336b/", // URL DARI SINI
                "GitHub" to "https://github.com/MTaufikIqball00" // URL DARI SINI
            )
        )
        val socialMediaLinks: LiveData<Map<String, String>> = _socialMediaLinks


}