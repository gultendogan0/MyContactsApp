package com.example.mycontactsapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.mycontactsapp.data.models.ContactsData

@Dao
interface ContactsDao {

    @Query("SELECT * FROM contacts_table ORDER BY id DESC LIMIT 10")
    fun getAllData(): LiveData<List<ContactsData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(contactsData: ContactsData)

    @Update
    suspend fun updateData(contactsData: ContactsData)

    @Delete
    suspend fun deleteItem(contactsData: ContactsData)

    @Query("DELETE FROM contacts_table")
    suspend fun deleteAll()

    @Query("SELECT*FROM contacts_table WHERE name LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): LiveData<List<ContactsData>>

    @Query("SELECT * FROM contacts_table WHERE groups = 'FAMILY'")
    fun getFamilyContacts(): LiveData<List<ContactsData>>

    @Query("SELECT * FROM contacts_table WHERE groups = 'FRIENDS'")
    fun getFriendsContacts(): LiveData<List<ContactsData>>

    @Query("SELECT * FROM contacts_table WHERE groups = 'WORK'")
    fun getWorkContacts(): LiveData<List<ContactsData>>

    @Query("SELECT * FROM contacts_table WHERE groups = 'OTHER'")
    fun getOtherContacts(): LiveData<List<ContactsData>>

    @Query("SELECT * FROM contacts_table WHERE groups = 'FAMILY' AND name LIKE :searchQuery")
    fun searchFamily(searchQuery: String): LiveData<List<ContactsData>>

    @Query("SELECT * FROM contacts_table WHERE groups = 'FRIENDS' AND name LIKE :searchQuery")
    fun searchFriends(searchQuery: String): LiveData<List<ContactsData>>

    @Query("SELECT * FROM contacts_table WHERE groups = 'WORK' AND name LIKE :searchQuery")
    fun searchWork(searchQuery: String): LiveData<List<ContactsData>>

    @Query("SELECT * FROM contacts_table WHERE groups = 'OTHER' AND name LIKE :searchQuery")
    fun searchOther(searchQuery: String): LiveData<List<ContactsData>>



}