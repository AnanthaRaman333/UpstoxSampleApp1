package com.example.upstoxholding.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.upstoxholding.databinding.HoldingsItemLayoutBinding
import com.example.upstoxholding.ui.main.model.repo.db.HoldingsEntity

class HoldingsListAdapter(diffCallback: DiffUtil.ItemCallback<HoldingsEntity>) :
    ListAdapter<HoldingsEntity, HoldingsListAdapter.HoldingsViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoldingsViewHolder {
        return HoldingsViewHolder(
            HoldingsItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class HoldingsViewHolder(private val binding: HoldingsItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(holdingsData: HoldingsEntity) = with(binding) {
            holdings = holdingsData
        }
    }

    override fun onBindViewHolder(holder: HoldingsViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}