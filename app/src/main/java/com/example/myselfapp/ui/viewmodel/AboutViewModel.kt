package com.example.myselfapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AboutViewModel @Inject constructor() : ViewModel() {

    private val _appName = MutableLiveData<String>("MySelfApp")
    val appName: LiveData<String> = _appName

    private val _appVersion = MutableLiveData<String>("1.0.0")
    val appVersion: LiveData<String> = _appVersion

    private val _developerName = MutableLiveData<String>("(Pengembang) " + "Muhammad Taufik Iqbal")
    val developerName: LiveData<String> = _developerName

    private val _aboutText = MutableLiveData<String>(
        "Aplikasi ini dibuat sebagai bagian dari proyek belajar MVVM, Hilt, dan Room. "
    )
    val aboutText: LiveData<String> = _aboutText
}