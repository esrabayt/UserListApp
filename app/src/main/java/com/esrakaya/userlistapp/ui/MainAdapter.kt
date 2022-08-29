package com.esrakaya.userlistapp.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.esrakaya.userlistapp.databinding.MainItemBinding
import com.esrakaya.userlistapp.utils.inflater

class MainAdapter : ListAdapter<MainItemViewData, MainAdapter.ViewHolder>(
    MainItemViewData.DIFF_CALLBACK
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            MainItemBinding.inflate(
                parent.context.inflater,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class ViewHolder(
        val binding: MainItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MainItemViewData) = with(binding) {
            tvPersonName.text = item.personWithId
        }
    }


}