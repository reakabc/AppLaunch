package com.reakabc.applaunch.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reakabc.applaunch.db.UserDatabase
import com.reakabc.applaunch.models.User
import com.reakabc.applaunch.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AddUserViewModel(private val userRepository: UserRepository) : ViewModel() {

    val addStatus:LiveData<String>
    get() = userRepository.addStatus

    fun addUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.addUser(user)
        }
    }

}