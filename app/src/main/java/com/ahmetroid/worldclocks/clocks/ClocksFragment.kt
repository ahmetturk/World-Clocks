package com.ahmetroid.worldclocks.clocks

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.ahmetroid.worldclocks.EventObserver
import com.ahmetroid.worldclocks.R
import com.ahmetroid.worldclocks.base.BaseFragment
import com.ahmetroid.worldclocks.databinding.FragmentClocksBinding

class ClocksFragment : BaseFragment<FragmentClocksBinding>(), ClocksAdapter.Callback {
    override val viewModel by viewModels<ClocksViewModel> {
        ClocksViewModelFactory(repository)
    }

    override fun getLayoutResId() = R.layout.fragment_clocks

    override fun onCreateView(savedInstanceState: Bundle?) {
        val clocksAdapter = ClocksAdapter(this)
        binding.clocksRecyclerView.adapter = clocksAdapter

        viewModel.clocks.observe(viewLifecycleOwner) { clocks ->
            clocksAdapter.setList(clocks)
        }

        viewModel.direction.observe(viewLifecycleOwner, EventObserver { navDirection ->
            findNavController().navigate(navDirection)
        })

        viewModel.filteredCities.observe(viewLifecycleOwner) { filteredCities ->
            clocksAdapter.showItemAdd(filteredCities.isNotEmpty())
        }

        viewModel.checkTimeDifference.observe(viewLifecycleOwner) {}
    }

    override fun onClockItemClick(position: Int, isAddItem: Boolean) {
        viewModel.clockItemClicked(position, isAddItem)
    }

}