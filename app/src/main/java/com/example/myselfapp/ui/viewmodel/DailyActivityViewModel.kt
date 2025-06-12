package com.example.myselfapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myselfapp.data.local.entity.DailyActivityEntity
import com.example.myselfapp.data.local.entity.FriendEntity
import com.example.myselfapp.data.repository.DailyActivityRepository
import com.example.myselfapp.data.repository.FriendRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DailyActivityViewModel @Inject constructor(
    private val dailyActivityRepository: DailyActivityRepository,
    private val friendRepository: FriendRepository // Injeksi FriendRepository
) : ViewModel() {

    private val _dailyActivities = MutableLiveData<List<DailyActivityEntity>>()
    val dailyActivities: LiveData<List<DailyActivityEntity>> = _dailyActivities

    private val _friendsList = MutableLiveData<List<FriendEntity>>()
    val friendsList: LiveData<List<FriendEntity>> = _friendsList

    fun loadDailyActivitiesAndFriends() {
        viewModelScope.launch {
            try {
                val activities = dailyActivityRepository.getDailyActivities()
                _dailyActivities.value = activities

                val friends = friendRepository.getFriends()
                _friendsList.value = friends
            } catch (e: Exception) {
                // Tangani kesalahan (misalnya, log error, tampilkan pesan ke pengguna)
                _dailyActivities.value = emptyList()
                _friendsList.value = emptyList()
            }
        }
    }
}