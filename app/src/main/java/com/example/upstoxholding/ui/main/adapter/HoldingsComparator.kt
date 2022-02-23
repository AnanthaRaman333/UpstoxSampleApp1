package com.example.upstoxholding.ui.main.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.upstoxholding.ui.main.model.repo.db.HoldingsEntity

object HoldingsComparator : DiffUtil.ItemCallback<HoldingsEntity>() {

    override fun areItemsTheSame(oldItem: HoldingsEntity, newItem: HoldingsEntity): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: HoldingsEntity, newItem: HoldingsEntity): Boolean {
        return oldItem == newItem
    }
}