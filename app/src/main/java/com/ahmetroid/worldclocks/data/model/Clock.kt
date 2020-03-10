package com.ahmetroid.worldclocks.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Clock(
    @PrimaryKey
    val cityName: String,
    val timeDifference: Int = 0,
    val backgroundColor: String = "",
    val clockColor: String = ""
) : Parcelable