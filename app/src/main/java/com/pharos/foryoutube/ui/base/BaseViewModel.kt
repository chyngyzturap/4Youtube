package com.pharos.foryoutube.ui.base

import androidx.lifecycle.ViewModel
import com.pharos.foryoutube.data.repository.BaseRepository


abstract class BaseViewModel(
    private val repository: BaseRepository
) : ViewModel() {
//    suspend fun logout(api: JobsApi) = repository.logout(api)
}