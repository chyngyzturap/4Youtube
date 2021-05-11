package com.pharos.foryoutube.data.repository

import com.pharos.foryoutube.data.network.YoutubeApi
import com.pharos.foryoutube.data.other.Constants.API_KEY
import com.pharos.foryoutube.data.other.Constants.CHANNEL_ID
import com.pharos.foryoutube.data.other.Constants.PART


class PlaylistRepository (
    private val api: YoutubeApi
    ) : BaseRepository() {

        suspend fun fetchAllPlaylist() = safeApiCall {
            api.fetchAllPlayList(API_KEY, PART, CHANNEL_ID)
        }
}