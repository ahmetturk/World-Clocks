package com.ahmetroid.worldclocks.clocks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ahmetroid.worldclocks.R
import com.ahmetroid.worldclocks.data.model.Clock
import com.ahmetroid.worldclocks.databinding.ItemClockBinding

class ClocksAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        private const val ITEM_CLOCK = 1
        private const val ITEM_ADD = 2
    }

    private var list: List<Clock> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            ITEM_CLOCK -> ItemClockViewHolder(
                ItemClockBinding.inflate(layoutInflater, parent, false)
            )
            else -> ItemAddViewHolder(
                layoutInflater.inflate(R.layout.item_add, parent, false)
            )
        }
    }

    override fun getItemCount() = list.size + 1

    override fun getItemViewType(position: Int) =
        when (position) {
            list.size -> ITEM_ADD
            else -> ITEM_CLOCK
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemClockViewHolder) {
            holder.bind(list[position])
        }
    }

    fun setList(newList: List<Clock>) {
        list = newList
        notifyDataSetChanged()
    }

}

class ItemClockViewHolder(private val binding: ItemClockBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(clock: Clock) {
        binding.clock = clock
    }
}

class ItemAddViewHolder(root: View) :
    RecyclerView.ViewHolder(root)