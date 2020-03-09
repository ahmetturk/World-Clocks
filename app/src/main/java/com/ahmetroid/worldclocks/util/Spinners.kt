package com.ahmetroid.worldclocks.util

import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.BindingAdapter

@BindingAdapter("list")
fun bindList(spinner: Spinner, list: List<String>) {
    spinner.adapter = ArrayAdapter(
        spinner.context,
        android.R.layout.simple_spinner_dropdown_item,
        list
    )
}

@BindingAdapter("selectedListener")
fun bindSelectedPosition(spinner: Spinner, listener: AdapterView.OnItemSelectedListener) {
    spinner.onItemSelectedListener = listener
}