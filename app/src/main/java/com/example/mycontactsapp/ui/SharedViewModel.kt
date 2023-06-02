package com.example.mycontactsapp.ui

import android.app.Application
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.mycontactsapp.R
import com.example.mycontactsapp.data.models.ContactsData
import com.example.mycontactsapp.data.models.Groups

class SharedViewModel(application: Application): AndroidViewModel(application) {

    val emptyDatabase: MutableLiveData<Boolean> = MutableLiveData(false)

    fun checkIfDatabaseEmpty(contactsData: List<ContactsData>){
        emptyDatabase.value = contactsData.isEmpty()
    }

    val listener: AdapterView.OnItemSelectedListener = object:
        AdapterView.OnItemSelectedListener{
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            when(position){
                0 -> {(parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.green))}
                1 -> {(parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application,R.color.yellow))}
                2 -> {(parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.red))}
                3 -> {(parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.darkGray))}
            }
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {}

    }

    fun verifyDataFromUser(name: String, no:String , address: String): Boolean{
        return if (TextUtils.isEmpty(name) || TextUtils.isEmpty(address)){
            false
        }else !(name.isEmpty() || address.isEmpty())
    }

    fun parsePriority(groups: String): Groups {
        return when(groups){
            "Family Group" -> {
                Groups.FAMILY}
            "Friends Group" -> {
                Groups.FRIENDS}
            "Work Group" -> {
                Groups.WORK}
            "Other Group" -> {
                Groups.OTHER}
            else -> {
                Groups.OTHER}
        }
    }

    fun parsePriority(groups: Groups) : Int{
        when(groups){
            Groups.FAMILY -> return 0
            Groups.FRIENDS -> return 1
            Groups.WORK -> return 2
            Groups.OTHER -> return 3
        }
    }
}