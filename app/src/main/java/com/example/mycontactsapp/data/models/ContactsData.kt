package com.example.mycontactsapp.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "contacts_table")
@Parcelize
data class ContactsData (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var name: String,
    var no: String,
    var groups: Groups,
    var address: String
): Parcelable