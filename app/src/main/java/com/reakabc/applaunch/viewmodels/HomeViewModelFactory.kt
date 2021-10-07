package com.reakabc.applaunch.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.reakabc.applaunch.repository.UserRepository
import com.reakabc.examapp.viewmodels.HomeViewModel

class HomeViewModelFactory(private val userRepository: UserRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(userRepository) as T
    }
}