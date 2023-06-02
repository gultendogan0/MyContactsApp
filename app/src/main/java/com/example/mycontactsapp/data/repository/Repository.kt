package com.example.mycontactsapp.data.repository

import com.example.mycontactsapp.data.ContactsDao
import com.example.mycontactsapp.data.models.ContactsData


class Repository(private val contactsDao: ContactsDao) {

    val getAllData = contactsDao.getAllData()

    suspend fun insertData(contactsData: ContactsData){
        contactsDao.insertData(contactsData)
    }

    suspend fun updateData(contactsData: ContactsData){
        contactsDao.updateData(contactsData)
    }

    suspend fun deleteItem(contactsData: ContactsData){
        contactsDao.deleteItem(contactsData)
    }

    suspend fun deleteAll(){
        contactsDao.deleteAll()
    }

    fun searchDatabase(searchQuery: String) = contactsDao.searchDatabase(searchQuery)

    fun searchFamily (searchQuery: String) = contactsDao.searchFamily(searchQuery)

    fun searchFriends (searchQuery: String) = contactsDao.searchFriends(searchQuery)

    fun searchWork (searchQuery: String) = contactsDao.searchWork(searchQuery)

    fun searchOther (searchQuery: String) = contactsDao.searchOther(searchQuery)

    val getFamilyContacts = contactsDao.getFamilyContacts()

    val getFriendsContacts = contactsDao.getFriendsContacts()

    val getWorkContacts = contactsDao.getWorkContacts()

    val getOtherContacts = contactsDao.getOtherContacts()
}