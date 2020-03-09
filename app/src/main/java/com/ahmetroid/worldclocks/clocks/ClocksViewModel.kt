package com.ahmetroid.worldclocks.clocks

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ahmetroid.worldclocks.base.BaseViewModel
import com.ahmetroid.worldclocks.data.Repository
import com.ahmetroid.worldclocks.data.model.City
import com.ahmetroid.worldclocks.data.model.Color
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ClocksViewModel(private val repository: Repository) : BaseViewModel() {

    val cities = MutableLiveData<List<City>>()

    val colors = MutableLiveData<List<Color>>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getResponse()
            cities.postValue(response.cities)
            colors.postValue(response.colors)
        }
    }

}

class ClocksViewModelFactory(
    private val repository: Repository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return ClocksViewModel(repository) as T
    }
}