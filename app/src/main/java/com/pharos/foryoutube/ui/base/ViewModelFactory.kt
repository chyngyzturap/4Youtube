package com.pharos.foryoutube.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pharos.foryoutube.data.repository.BaseRepository
import com.pharos.foryoutube.data.repository.PlaylistRepository
import com.pharos.foryoutube.ui.playlist.PlaylistViewModel


@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val repository: BaseRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(PlaylistViewModel::class.java) -> PlaylistViewModel(repository as PlaylistRepository) as T
            else -> throw IllegalArgumentException("ViewModelClass not found")
        }
    }
}