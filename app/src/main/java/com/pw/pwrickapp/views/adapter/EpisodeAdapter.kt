package com.pw.pwrickapp.views.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.pw.pwrickapp.databinding.EpisodeItemBinding
import com.pw.pwrickapp.utils.CallbackListener


/**
 * Created by Ritik on: 31/08/24
 */

class EpisodeAdapter (private val context: Context, private val list: ArrayList<String>) :
    RecyclerView.Adapter<EpisodeAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: EpisodeItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            EpisodeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        holder.binding.episodeLink.text = item

        holder.itemView.setOnClickListener {
            Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
        }

    }
}
