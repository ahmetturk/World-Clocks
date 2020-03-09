package com.ahmetroid.worldclocks.detail

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import com.ahmetroid.worldclocks.R
import com.ahmetroid.worldclocks.base.BaseFragment
import com.ahmetroid.worldclocks.databinding.FragmentDetailBinding
import timber.log.Timber

class DetailFragment : BaseFragment<FragmentDetailBinding>() {
    override val viewModel by viewModels<DetailViewModel> {
        DetailViewModelFactory(args)
    }

    override fun getLayoutResId() = R.layout.fragment_detail

    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(savedInstanceState: Bundle?) {
        super.onCreateView(savedInstanceState)

        viewModel.cityNamePosition.observe(viewLifecycleOwner) {
            Timber.d("cityNameSelectedPosition $it")
        }

        viewModel.backgroundColorPosition.observe(viewLifecycleOwner) {
            Timber.d("backgroundColorSelectedPosition $it")
        }

        viewModel.clockColorPosition.observe(viewLifecycleOwner) {
            Timber.d("clockColorSelectedPosition $it")
        }

    }
}