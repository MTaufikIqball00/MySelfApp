package com.example.myselfapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myselfapp.data.local.entity.GalleryItemEntity
import com.example.myselfapp.data.repository.GalleryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val galleryRepository: GalleryRepository
) : ViewModel() {

    private val _galleryItems = MutableLiveData<List<GalleryItemEntity>>()
    val galleryItems: LiveData<List<GalleryItemEntity>> = _galleryItems

    fun loadGalleryItems() {
        viewModelScope.launch {
            try {
                val items = galleryRepository.getGalleryItems()
                _galleryItems.value = items
            } catch (e: Exception) {
                _galleryItems.value = emptyList()
            }
        }
    }
}