package com.ahmetroid.worldclocks.data.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class City(
    val name: String,
    val timeDifference: Int
) : Parcelable