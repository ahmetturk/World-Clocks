package com.ahmetroid.worldclocks.clocks

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.ahmetroid.worldclocks.R
import com.ahmetroid.worldclocks.base.BaseFragment
import com.ahmetroid.worldclocks.databinding.FragmentClocksBinding

class ClocksFragment : BaseFragment<FragmentClocksBinding>() {
    override val viewModel by viewModels<ClocksViewModel> {
        ClocksViewModelFactory(repository)
    }

    override fun getLayoutResId() = R.layout.fragment_clocks

    override fun onCreateView(savedInstanceState: Bundle?) {
        val clocksAdapter = ClocksAdapter()
        binding.clocksRecyclerView.adapter = clocksAdapter
    }
}