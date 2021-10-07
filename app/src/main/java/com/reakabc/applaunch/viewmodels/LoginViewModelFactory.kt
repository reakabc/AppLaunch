package com.reakabc.applaunch.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.reakabc.applaunch.repository.UserRepository

class LoginViewModelFactory(private val userRepository: UserRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(userRepository) as T
    }
}