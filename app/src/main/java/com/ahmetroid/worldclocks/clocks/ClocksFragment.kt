package com.ahmetroid.worldclocks.clocks

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.viewModels
import com.ahmetroid.worldclocks.R
import com.ahmetroid.worldclocks.base.BaseFragment
import com.ahmetroid.worldclocks.databinding.FragmentClocksBinding

class ClocksFragment : BaseFragment<FragmentClocksBinding>() {
    override val viewModel by viewModels<ClocksViewModel>()

    override fun getLayoutResId() = R.layout.fragment_clocks

    override fun onCreateView(savedInstanceState: Bundle?) {
        Log.d("a", repository.toString())
    }
}