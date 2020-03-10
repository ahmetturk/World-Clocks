package com.ahmetroid.worldclocks.clocks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ahmetroid.worldclocks.R
import com.ahmetroid.worldclocks.data.model.Clock
import com.ahmetroid.worldclocks.databinding.ItemClockBinding

class ClocksAdapter(private val callback: Callback) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        private const val ITEM_CLOCK = 1
        private const val ITEM_ADD = 2
    }

    interface Callback {
        fun onClockItemClick(position: Int = RecyclerView.NO_POSITION, isAddItem: Boolean = false)
    }

    private var list: List<Clock> = emptyList()
    private var showItemAdd = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            ITEM_CLOCK -> ItemClockViewHolder(
                ItemClockBinding.inflate(layoutInflater, parent, false), callback
            )
            else -> ItemAddViewHolder(
                layoutInflater.inflate(R.layout.item_add, parent, false), callback
            )
        }
    }

    override fun getItemCount() = if (showItemAdd) list.size + 1 else list.size

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

    fun showItemAdd(show: Boolean) {
        if (showItemAdd != show) {
            showItemAdd = show
            if (showItemAdd) {
                notifyItemInserted(list.size)
            } else {
                notifyItemRemoved(list.size)
            }
        }
    }
}

class ItemClockViewHolder(
    private val binding: ItemClockBinding,
    private val callback: ClocksAdapter.Callback
) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(clock: Clock) {
        binding.clock = clock
        binding.root.setOnClickListener {
            callback.onClockItemClick(position = layoutPosition)
        }
    }
}

class ItemAddViewHolder(root: View, callback: ClocksAdapter.Callback) :
    RecyclerView.ViewHolder(root) {
    init {
        root.setOnClickListener {
            callback.onClockItemClick(isAddItem = true)
        }
    }
}