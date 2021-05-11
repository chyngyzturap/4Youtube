package com.pharos.foryoutube.ui.playlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pharos.foryoutube.data.network.Resource
import com.pharos.foryoutube.data.repository.PlaylistRepository
import com.pharos.foryoutube.data.response.PlayList
import com.pharos.foryoutube.ui.base.BaseViewModel
import kotlinx.coroutines.launch


class PlaylistViewModel (
    private val repository: PlaylistRepository
    ) : BaseViewModel(repository) {

        private val _playlist: MutableLiveData<Resource<PlayList>> = MutableLiveData()
    val playlist: MutableLiveData<Resource<PlayList>> get() = _playlist

        fun fetchAllPlaylist() = viewModelScope.launch {
            _playlist.value = Resource.Loading
            _playlist.value = repository.fetchAllPlaylist()
        }
}