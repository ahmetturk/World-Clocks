package com.ahmetroid.worldclocks.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Clock(
    val cityName: String,
    val timeDifference: Int,
    val backgroundColor: String,
    val clockColor: String
) : Parcelable