package com.pharos.foryoutube.ui.playlist

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.pharos.foryoutube.data.network.Resource
import com.pharos.foryoutube.data.network.YoutubeApi
import com.pharos.foryoutube.data.repository.PlaylistRepository
import com.pharos.foryoutube.data.response.Items
import com.pharos.foryoutube.databinding.FragmentAllPlaylistsBinding
import com.pharos.foryoutube.ui.adapter.OnItem
import com.pharos.foryoutube.ui.adapter.OnItemDetail
import com.pharos.foryoutube.ui.adapter.PlayListsAdapter
import com.pharos.foryoutube.ui.base.BaseFragment
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking


class AllPlaylistsFragment : BaseFragment<PlaylistViewModel, FragmentAllPlaylistsBinding, PlaylistRepository>(),
        OnItem {

    private lateinit var adapter: PlayListsAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

viewModel.fetchAllPlaylist()


        viewModel.playlist.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
//                    binding.progressbar.visible(false)
                    Log.e("ololo", "onViewCreated success: ${it.value.items}")

                    adapter.addItems(it.value.items.toMutableList())
                }
                is Resource.Loading -> {
//                    binding.progressbar.visible(true)

                }
                is Resource.Failure -> {
                    Log.e("ololo", "onViewCreated error: ${it.errorBody}")
                }
            }
        })
    }
    override fun getViewModel() = PlaylistViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    )= FragmentAllPlaylistsBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): PlaylistRepository {
        val token = runBlocking { userPreferences.tokenAccess.first() }
        val api = remoteDataSource.buildApiWithoutToken(YoutubeApi::class.java, token)
        return PlaylistRepository(api)
    }

    override fun onPlayListItemClick(playList: Items) {
        val intent = Intent(requireContext(), OnePlaylistFragment::class.java)
        intent.putExtra("playlistId", playList.id)
        intent.putExtra("title", playList.snippet.title)
        intent.putExtra("desc", playList.snippet.description)
        startActivity(intent)
    }


}