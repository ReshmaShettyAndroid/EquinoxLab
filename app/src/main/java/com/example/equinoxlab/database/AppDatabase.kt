package com.example.equinoxlab.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.equinoxlab.database.dao.ManagerDao
import com.example.equinoxlab.database.entity.ManagerEntity

@Database(entities = [(ManagerEntity::class)], version = 5, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun ManagerDao(): ManagerDao


    companion object {

        private var sInstance:AppDatabase? = null
        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (sInstance == null) {
                sInstance = Room
                    .databaseBuilder(context.applicationContext, AppDatabase::class.java, "managerdb")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return sInstance!!
        }
    }

}