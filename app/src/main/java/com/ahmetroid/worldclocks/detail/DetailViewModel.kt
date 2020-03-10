package com.ahmetroid.worldclocks.detail

import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ahmetroid.worldclocks.R
import com.ahmetroid.worldclocks.base.BaseViewModel
import com.ahmetroid.worldclocks.data.Repository
import com.ahmetroid.worldclocks.data.model.Clock
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(
    args: DetailFragmentArgs,
    private val repository: Repository
) : BaseViewModel() {

    val cityNamesWithTimes = args.response.cities.map { "${it.name} (${it.timeDifference})" }
    val colorNames = args.response.colors.map { it.name }
    private val cityNames = args.response.cities.map { it.name }
    private val timeDifferences = args.response.cities.map { it.timeDifference }
    private val colorCodes = args.response.colors.map { it.code }

    var cityNamePosition = MutableLiveData(0)
    var backgroundColorPosition = MutableLiveData(0)
    var clockColorPosition = MutableLiveData(0)

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

    init {
        args.clock?.let {
            cityNamePosition.value = cityNames.indexOf(it.cityName)
            backgroundColorPosition.value = colorCodes.indexOf(it.backgroundColor)
            clockColorPosition.value = colorCodes.indexOf(it.clockColor)
        }
    }

    fun okButtonClicked() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertToDb(
                Clock(
                    cityNames[cityNamePosition.value!!],
                    timeDifferences[cityNamePosition.value!!],
                    colorCodes[backgroundColorPosition.value!!],
                    colorCodes[clockColorPosition.value!!]
                )
            )
        }
    }

    fun cancelButtonClicked() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFromDb(
                Clock(cityNames[cityNamePosition.value!!])
            )
        }
    }

}

class DetailViewModelFactory(
    private val args: DetailFragmentArgs,
    private val repository: Repository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return DetailViewModel(args, repository) as T
    }
}