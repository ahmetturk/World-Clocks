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
fun bindSelectedListener(spinner: Spinner, listener: AdapterView.OnItemSelectedListener) {
    spinner.onItemSelectedListener = listener
}

@BindingAdapter("position")
fun bindPosition(spinner: Spinner, position: Int) {
    if (spinner.selectedItemPosition != position) {
        spinner.setSelection(position)
    }
}