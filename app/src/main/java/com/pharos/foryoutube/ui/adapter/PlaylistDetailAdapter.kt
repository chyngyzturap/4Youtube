package com.pharos.foryoutube.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pharos.foryoutube.data.response.Items
import com.pharos.foryoutube.databinding.ItemPlaylistBinding
import com.pharos.foryoutube.utils.loadImage


class PlaylistDetailAdapter(private val listener: OnItemDetail): RecyclerView.Adapter<PlaylistDetailViewHolder>() {

    private var _binding: ItemPlaylistBinding? = null

    private var playList: MutableList<Items> = mutableListOf()


    fun addItems(list: MutableList<Items>) {
        playList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistDetailViewHolder {
        _binding = ItemPlaylistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlaylistDetailViewHolder(_binding!!, listener)
    }

    override fun onBindViewHolder(detailViewHolder: PlaylistDetailViewHolder, position: Int) {
        detailViewHolder.onBind(playList[position])
    }

    override fun getItemCount() = playList.size
}

class PlaylistDetailViewHolder(private val binding: ItemPlaylistBinding, var listener: OnItemDetail)
    : RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun onBind(playList: Items) {

        binding.tvTitle.text = playList.snippet.title
        binding.tvDuration.text = playList.contentDetails.itemCount.toString() + " Videos"

        binding.ivPreview.loadImage(itemView.context,
            playList.snippet.thumbnails.default.url)

        itemView.setOnClickListener {
            listener.onPlayListItemClick(playList)

        }

    }
}

interface OnItemDetail {
    fun onPlayListItemClick(playList: Items)
}
