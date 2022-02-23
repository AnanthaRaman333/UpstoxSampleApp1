package com.example.upstoxholding.ui.main.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.upstoxholding.ui.main.model.repo.db.HoldingsEntity

class BindingAdapter {

    companion object {

        @BindingAdapter("profit_loss")
        @JvmStatic
        fun setProfitLoss(textview: TextView, data: HoldingsEntity) {
            textview.text = String.format("₹%.2f", (data.ltp - data.avgPrice) * data.quantity)
        }

        @BindingAdapter("last_trade_price")
        @JvmStatic
        fun setLastTradePrice(textview: TextView, data: HoldingsEntity) {
            textview.text = String.format("₹%.2f", data.ltp)
        }
    }
}