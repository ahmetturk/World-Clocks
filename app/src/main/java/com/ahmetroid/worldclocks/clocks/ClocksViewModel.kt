package com.ahmetroid.worldclocks.clocks

import androidx.lifecycle.*
import androidx.navigation.NavDirections
import com.ahmetroid.worldclocks.Event
import com.ahmetroid.worldclocks.base.BaseViewModel
import com.ahmetroid.worldclocks.data.Repository
import com.ahmetroid.worldclocks.data.model.Clock
import com.ahmetroid.worldclocks.data.model.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ClocksViewModel(private val repository: Repository) : BaseViewModel() {

    private val _response = MutableLiveData<Response>()
    val response: LiveData<Response>
        get() = _response

    val clocks =
        response.map { response ->
            response.cities.map { city ->
                Clock(
                    city.name,
                    city.timeDifference,
                    response.colors[0].code,
                    response.colors[1].code
                )
            }
        }

    private val _direction = MutableLiveData<Event<NavDirections>>()
    val direction: LiveData<Event<NavDirections>>
        get() = _direction

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val data = repository.getResponse()
            _response.postValue(data)
        }
    }

    fun clockItemClicked(position: Int, isAddItem: Boolean) {
        val clock = if (isAddItem.not()) {
            clocks.value?.get(position)
        } else {
            null
        }

        response.value?.let { response ->
            _direction.value = Event(
                ClocksFragmentDirections.actionClocksFragmentToDetailFragment(
                    response,
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