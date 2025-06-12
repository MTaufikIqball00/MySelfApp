package com.example.myselfapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myselfapp.data.local.entity.MusicVideoEntity
import com.example.myselfapp.data.repository.MusicVideoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MusicVideoViewModel @Inject constructor(
    private val musicVideoRepository: MusicVideoRepository
) : ViewModel() {

    private val _musicVideos = MutableLiveData<List<MusicVideoEntity>>()
    val musicVideos: LiveData<List<MusicVideoEntity>> = _musicVideos

    fun loadMusicVideos() {
        viewModelScope.launch {
            try {
                val videos = musicVideoRepository.getMusicVideos()
                _musicVideos.value = videos
            } catch (e: Exception) {
                _musicVideos.value = emptyList()
            }
        }
    }
}