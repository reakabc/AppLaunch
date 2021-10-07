package com.reakabc.applaunch.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.reakabc.applaunch.repository.UserRepository

class AddUserViewModelFactory(private val userRepository: UserRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AddUserViewModel(userRepository) as T
    }
}