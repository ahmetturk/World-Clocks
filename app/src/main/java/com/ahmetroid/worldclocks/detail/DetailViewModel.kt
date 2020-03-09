package com.ahmetroid.worldclocks.detail

import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ahmetroid.worldclocks.R
import com.ahmetroid.worldclocks.base.BaseViewModel

class DetailViewModel(args: DetailFragmentArgs) : BaseViewModel() {

    val cityNames = args.response.cities.map { "${it.name} (${it.timeDifference})" }
    val colorNames = args.response.colors.map { it.name }

    val cityNamePosition = MutableLiveData<Int>()
    val backgroundColorPosition = MutableLiveData<Int>()
    val clockColorPosition = MutableLiveData<Int>()

    val spinnerSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            when (parent.id) {
                R.id.cityNameSpinner -> cityNamePosition.value = position
                R.id.backgroundColorSpinner -> backgroundColorPosition.value = position
                R.id.clockColorSpinner -> clockColorPosition.value = position
            }
        }

        override fun onNothingSelected(parent: AdapterView<*>) {}
    }

}

class DetailViewModelFactory(
    private val args: DetailFragmentArgs
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return DetailViewModel(args) as T
    }
}