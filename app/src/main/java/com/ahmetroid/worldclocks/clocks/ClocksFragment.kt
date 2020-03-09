package com.ahmetroid.worldclocks.clocks

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.ahmetroid.worldclocks.R
import com.ahmetroid.worldclocks.base.BaseFragment
import com.ahmetroid.worldclocks.databinding.FragmentClocksBinding
import timber.log.Timber

class ClocksFragment : BaseFragment<FragmentClocksBinding>() {
    override val viewModel by viewModels<ClocksViewModel> {
        ClocksViewModelFactory(repository)
    }

    override fun getLayoutResId() = R.layout.fragment_clocks

    override fun onCreateView(savedInstanceState: Bundle?) {
        viewModel.cities.observe(viewLifecycleOwner) {
            Timber.d(it.toString())
        }
    }
}