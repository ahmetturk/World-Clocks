package com.ahmetroid.worldclocks.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.ahmetroid.worldclocks.BR
import com.ahmetroid.worldclocks.app.WorldClocks

abstract class BaseFragment<T : ViewDataBinding> : Fragment() {

    protected lateinit var binding: T

    abstract val viewModel: BaseViewModel

    abstract fun getLayoutResId(): Int

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<T>(inflater, getLayoutResId(), container, false)
            .apply {
                setVariable(BR.viewModel, viewModel)
                lifecycleOwner = this@BaseFragment
            }
        onCreateView(savedInstanceState)
        return binding.root
    }

    open fun onCreateView(savedInstanceState: Bundle?) {}

    val repository
        get() = (requireContext().applicationContext as WorldClocks).repository


}