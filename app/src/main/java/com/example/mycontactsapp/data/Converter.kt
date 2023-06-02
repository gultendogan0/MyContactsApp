package com.example.mycontactsapp.data

import androidx.room.TypeConverter
import com.example.mycontactsapp.data.models.Groups

class Converter {

    @TypeConverter
    fun fromGroups(groups: Groups): String{
        return groups.name
    }

    @TypeConverter
    fun toGroups(groups: Groups): Groups{
        return Groups.valueOf(groups.toString())
    }
}