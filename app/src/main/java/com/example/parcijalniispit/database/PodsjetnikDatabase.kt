package com.example.parcijalniispit.database

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.parcijalniispit.dao.UserDao
import com.example.parcijalniispit.model.User


@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class PodsjetnikDatabase: RoomDatabase(){

    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE : PodsjetnikDatabase? = null

        fun getDatabase(context: Context) : PodsjetnikDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PodsjetnikDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}