package com.pharos.foryoutube.data.network

import com.pharos.foryoutube.data.response.PlayList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeApi {

@GET("playlist")
    suspend fun fetchAllPlayList(
        @Query("part") part: String,
        @Query("channelId") channelId: String,
        @Query("key") apiKey: String
    ) : PlayList
}