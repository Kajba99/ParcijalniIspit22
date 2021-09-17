package com.example.parcijalniispit.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import com.example.parcijalniispit.dao.UserDao
import com.example.parcijalniispit.model.User

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }


    suspend fun deleteUser(user: User){
        userDao.deleteUser(user)
    }


}