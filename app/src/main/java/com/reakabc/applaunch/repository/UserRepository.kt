package com.reakabc.applaunch.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.reakabc.applaunch.db.UserDatabase
import com.reakabc.applaunch.models.User
import com.reakabc.applaunch.utils.SingleLiveData

class UserRepository(
    private val userDatabase: UserDatabase,
    private val applicationContext: Context,
) {

    private val userLiveData = SingleLiveData<List<User>>()
    val user: MutableLiveData<List<User>>
        get() = userLiveData

    private val addStatusLiveData = SingleLiveData<String>()
    val addStatus: MutableLiveData<String>
        get() = addStatusLiveData

    suspend fun getUsers(){
        try {
            val list:List<User> = userDatabase.userDao().getUser()
            userLiveData.postValue(list)
        }catch (e:Exception){
            Log.d("USER REPOSITORY", e.message.toString())
        }
    }

    suspend fun addUser(user: User){
        try {
            userDatabase.userDao().insertUser(user)
            // update live data
            val list:List<User> = userDatabase.userDao().getUser()
            userLiveData.postValue(list)
        }catch (e:Exception){
            Log.d("USER REPOSITORY", e.message.toString())
        }
        addStatusLiveData.postValue("added")
    }

    suspend fun deleteUser(user: User){
        try {
            userDatabase.userDao().deleteUser(user)
            // update live data
            val list:List<User> = userDatabase.userDao().getUser()
            userLiveData.postValue(list)
        }catch (e:Exception){
            Log.d("USER REPOSITORY", e.message.toString())
        }
    }

}