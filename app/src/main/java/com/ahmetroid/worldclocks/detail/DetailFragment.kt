package com.ahmetroid.worldclocks.detail

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.ahmetroid.worldclocks.R
import com.ahmetroid.worldclocks.base.BaseFragment
import com.ahmetroid.worldclocks.databinding.FragmentDetailBinding

class DetailFragment : BaseFragment<FragmentDetailBinding>() {
    override val viewModel by viewModels<DetailViewModel> {
        DetailViewModelFactory(args, repository)
    }

    override fun getLayoutResId() = R.layout.fragment_detail

    private val args: DetailFragmentArgs by navArgs()
}