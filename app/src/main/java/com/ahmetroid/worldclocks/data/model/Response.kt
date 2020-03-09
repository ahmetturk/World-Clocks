package com.ahmetroid.worldclocks.data.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Response(
    val cities: List<City>,
    val colors: List<Color>
) : Parcelable