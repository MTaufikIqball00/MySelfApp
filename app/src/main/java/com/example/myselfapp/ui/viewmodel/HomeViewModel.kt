package com.example.myselfapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myselfapp.data.model.UserProfile
import com.example.myselfapp.data.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
): ViewModel() {

    private val _userProfile = MutableLiveData<UserProfile?>()
    val userProfile: LiveData<UserProfile?> = _userProfile

    private val _interests = MutableLiveData<List<String>>()
    val interests: LiveData<List<String>> = _interests

    private val _hobbies = MutableLiveData<List<String>>()
    val hobbies: LiveData<List<String>> = _hobbies

    private val _makes = MutableLiveData<List<String>>()
    val makes: LiveData<List<String>> = _makes

    private val _dreams = MutableLiveData<List<String>>()
    val dreams: LiveData<List<String>> = _dreams

    fun loadUserProfile() {
        viewModelScope.launch {
            // Fetch data from repository, which might be from ROOM
            _userProfile.value = profileRepository.getUserProfile()
            _interests.value = profileRepository.getUserInterests()
            _hobbies.value = profileRepository.getUserHobbies()
            _makes.value = profileRepository.getUserMakes()
            _dreams.value = profileRepository.getUserDreams()
        }
    }

}