package com.example.mycontactsapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mycontactsapp.data.models.ContactsData

@Database(entities = [ContactsData::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class ContactsDatabase: RoomDatabase() {

    abstract fun contactsDao(): ContactsDao

    companion object{
        @Volatile
        private var INSTANCE: ContactsDatabase? = null

        fun getDatabase(context: Context): ContactsDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null) return tempInstance

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ContactsDatabase::class.java,
                    "contacts_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}


