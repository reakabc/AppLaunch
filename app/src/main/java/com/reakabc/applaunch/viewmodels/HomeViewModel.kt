package com.reakabc.examapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reakabc.applaunch.db.UserDatabase
import com.reakabc.applaunch.models.User
import com.reakabc.applaunch.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HomeViewModel(private val userRepository: UserRepository) : ViewModel() {

    val list:LiveData<List<User>>
    get() = userRepository.user

    fun getUserList(){
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.getUsers()
        }
    }

    fun deleteUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.deleteUser(user)
        }
    }

}