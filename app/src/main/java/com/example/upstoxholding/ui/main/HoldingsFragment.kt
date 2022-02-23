package com.example.upstoxholding.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.upstoxholding.R
import com.example.upstoxholding.databinding.MainFragmentBinding
import com.example.upstoxholding.ui.main.adapter.HoldingsComparator
import com.example.upstoxholding.ui.main.adapter.HoldingsListAdapter
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HoldingsFragment : Fragment() {

    companion object {
        fun newInstance() = HoldingsFragment()
    }

    private val viewModel: HoldingsViewModel by viewModels()

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    private lateinit var binding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inits()
    }

    private fun inits() {

        val adapter = HoldingsListAdapter(HoldingsComparator)
        binding.holdingsRecyclerview.adapter = adapter

        viewModel.allHoldings.observe(viewLifecycleOwner) { holdings ->
            holdings.let { adapter.submitList(holdings) }
        }

        val itemDecoration: RecyclerView.ItemDecoration =
            DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        binding.holdingsRecyclerview.addItemDecoration(itemDecoration)

        bottomSheetBehavior = BottomSheetBehavior.from(binding.summaryInclude.summaryBtmSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN

        binding.profitLossText.setOnClickListener {
            if (bottomSheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                binding.profitLossText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_down_arrow, 0,0,0)
            } else {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
                binding.profitLossText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_up_arrow, 0,0,0)
            }
        }

        updateSummary()
    }

    private fun updateSummary() {

        viewModel.totalPl.observe(viewLifecycleOwner) {
            it?.let {
                binding.profitLossValue.text = String.format("₹%.2f", it)
            }
        }
        with(binding.summaryInclude) {
            viewModel.dayPl.observe(viewLifecycleOwner) {
                it?.let {
                    this.todayProfitLossValue.text = String.format("₹%.2f", it)
                }
            }

            viewModel.currentValue.observe(viewLifecycleOwner) {
                it?.let {
                    this.currentInvestValue.text = String.format("₹%.2f", it)
                }
            }

            viewModel.totalInvestment.observe(viewLifecycleOwner) {
                it?.let { this.totalInvestValue.text = String.format("₹%.2f", it) }
            }
        }
    }
}