package com.ahmetroid.worldclocks.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

inline fun <T> MediatorLiveData<T>.addSources(
    sources: List<LiveData<out Any>>,
    crossinline onChanged: () -> T
): MediatorLiveData<T> {
    sources.forEach {
        addSource(it) { value = onChanged() }
    }
    return this
}