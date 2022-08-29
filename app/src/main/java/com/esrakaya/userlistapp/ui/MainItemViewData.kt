package com.esrakaya.userlistapp.ui

import androidx.recyclerview.widget.DiffUtil
import com.esrakaya.userlistapp.domain.model.Person

data class MainItemViewData(
    val person: Person
) {
    val personWithId: String
        get() = "${person.fullName} (${person.id})"

    companion object {
        val DIFF_CALLBACK
            get() = object : DiffUtil.ItemCallback<MainItemViewData>() {
                override fun areItemsTheSame(
                    oldItem: MainItemViewData,
                    newItem: MainItemViewData
                ) = oldItem.person.id == newItem.person.id

                override fun areContentsTheSame(
                    oldItem: MainItemViewData,
                    newItem: MainItemViewData
                ) = oldItem == newItem
            }
    }
}
