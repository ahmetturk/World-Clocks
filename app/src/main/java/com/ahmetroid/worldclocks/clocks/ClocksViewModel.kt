package com.ahmetroid.worldclocks.clocks

import androidx.lifecycle.*
import androidx.navigation.NavDirections
import com.ahmetroid.worldclocks.Event
import com.ahmetroid.worldclocks.base.BaseViewModel
import com.ahmetroid.worldclocks.data.Repository
import com.ahmetroid.worldclocks.data.model.City
import com.ahmetroid.worldclocks.data.model.Clock
import com.ahmetroid.worldclocks.data.model.Response
import com.ahmetroid.worldclocks.util.addSources
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ClocksViewModel(private val repository: Repository) : BaseViewModel() {

    private val response = MutableLiveData<Response>()
    val clocks = repository.getClocks()

    val filteredCities = MediatorLiveData<List<City>>()
        .addSources(listOf(response, clocks)) {
            val clocksData = clocks.value
            val citiesData = response.value?.cities
            if (clocksData != null && citiesData != null) {
                citiesData.filter { city ->
                    clocksData.none { it.cityName == city.name }
                }
            } else {
                emptyList()
            }
        }


    private val _direction = MutableLiveData<Event<NavDirections>>()
    val direction: LiveData<Event<NavDirections>>
        get() = _direction

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val data = repository.getResponse()
            response.postValue(data)
        }
    }

    fun clockItemClicked(position: Int, isAddItem: Boolean) {
        var clock: Clock? = null
        var cities: List<City> = filteredCities.value!!

        if (isAddItem.not()) {
            clock = clocks.value!!.get(position)
            cities = cities.plus(City(clock.cityName, clock.timeDifference))
        }

        response.value?.let { response ->
            _direction.value = Event(
                ClocksFragmentDirections.actionClocksFragmentToDetailFragment(
                    response.copy(cities = cities),
                    clock
                )
            )
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