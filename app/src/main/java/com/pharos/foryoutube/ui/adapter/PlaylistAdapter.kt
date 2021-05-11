package com.pharos.foryoutube.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pharos.foryoutube.data.response.Items
import com.pharos.foryoutube.databinding.ItemListOfPlaylistBinding
import com.pharos.foryoutube.utils.loadImage

class PlayListsAdapter( private val listener: OnItem): RecyclerView.Adapter<PlaylistViewHolder>() {

    private var _binding: ItemListOfPlaylistBinding? = null

    private var playList: MutableList<Items> = mutableListOf()


    fun addItems(list: MutableList<Items>) {
        playList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        _binding = ItemListOfPlaylistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlaylistViewHolder(_binding!!, listener)
    }

    override fun onBindViewHolder(detailViewHolder: PlaylistViewHolder, position: Int) {
        detailViewHolder.onBind(playList[position])
    }

    override fun getItemCount() = playList.size
}

class PlaylistViewHolder(private val binding: ItemListOfPlaylistBinding, var listener: OnItem)
    : RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun onBind(playList: Items) {

        binding.tvTitle.text = playList.snippet.title
        binding.tvCounts.text = playList.contentDetails.duration.toString() + " Videos"

        binding.ivPreview.loadImage(itemView.context,
            playList.snippet.thumbnails.default.url)

        itemView.setOnClickListener {
            listener.onPlayListItemClick(playList)

        }

    }
}

interface OnItem {
    fun onPlayListItemClick(playList: Items)
}
