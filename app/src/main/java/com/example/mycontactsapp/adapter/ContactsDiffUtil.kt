package com.example.mycontactsapp.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.mycontactsapp.data.models.ContactsData

class ContactsDiffUtil(
    private val oldList: List<ContactsData>,
    private val newList: List<ContactsData>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
                && oldList[oldItemPosition].name == newList[newItemPosition].name
                && oldList[oldItemPosition].no == newList[newItemPosition].no
                && oldList[oldItemPosition].address == newList[newItemPosition].address
                && oldList[oldItemPosition].groups == newList[newItemPosition].groups


    }
}