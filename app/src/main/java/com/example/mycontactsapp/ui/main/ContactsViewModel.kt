package com.example.mycontactsapp.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycontactsapp.data.ContactsDatabase
import com.example.mycontactsapp.data.models.ContactsData
import com.example.mycontactsapp.data.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactsViewModel(application: Application): AndroidViewModel(application) {

    private val contactsDao =ContactsDatabase.getDatabase(application).contactsDao()
    private val repository = Repository(contactsDao)
    val getAllData =repository.getAllData

    fun insertData(contactsData: ContactsData){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(contactsData)
        }
    }

    fun updateData(contactsData: ContactsData){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateData(contactsData)
        }
    }

    fun deleteItem(contactsData: ContactsData){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteItem(contactsData)
        }
    }

    fun deleteAll(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }

    fun searchDatabase(searchQuery: String) = repository.searchDatabase(searchQuery)

    fun searchFamily(searchQuery: String) = repository.searchFamily(searchQuery)

    fun searchFriends(searchQuery: String) = repository.searchFriends(searchQuery)

    fun searchWork(searchQuery: String) = repository.searchWork(searchQuery)

    fun searchOther(searchQuery: String) = repository.searchOther(searchQuery)

    val getFamilyContacts = repository.getFamilyContacts

    val getFriendsContacts = repository.getFriendsContacts

    val getWorkContacts = repository.getWorkContacts

    val getOtherContacts = repository.getOtherContacts
}