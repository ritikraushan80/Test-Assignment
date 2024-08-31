package com.pw.pwrickapp.views.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.pw.pwrickapp.R
import com.pw.pwrickapp.databinding.ItemCharacterBinding
import com.pw.pwrickapp.model.Results
import com.pw.pwrickapp.utils.CallbackListener


/**
 * Created by Ritik on: 31/08/24
 */

class CharacterAdapter( private val list: ArrayList<Results>, private val onClick: CallbackListener) :
    RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        holder.binding.nameTextView.text = item.name
        holder.binding.gender.text = item.gender

        holder.binding.imgRicks.load(item.image) {
            crossfade(true)
            placeholder(R.drawable.logo)
        }

        holder.itemView.setOnClickListener {
            item.id?.let { it1 -> onClick.clickOnCharacterItem(it1) }
        }

    }


}
