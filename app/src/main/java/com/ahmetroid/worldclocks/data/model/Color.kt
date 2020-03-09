package com.ahmetroid.worldclocks.data.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Color(
    val name: String,
    val code: String
) : Parcelable