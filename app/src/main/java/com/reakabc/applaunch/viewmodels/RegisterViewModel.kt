package com.reakabc.applaunch.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.reakabc.applaunch.repository.UserRepository
import com.reakabc.applaunch.utils.Validators.Companion.isValidEmail
import com.reakabc.applaunch.utils.Validators.Companion.isValidName
import com.reakabc.applaunch.utils.Validators.Companion.isValidPassword


class RegisterViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val statusLiveData = MutableLiveData<String>()

    val status: MutableLiveData<String>
        get() = statusLiveData

    fun register(name:String, email:String, password:String){

        if (!isValidName(name)) {
            statusLiveData.postValue("invalid-name")
        }else if (!isValidEmail(email)) {
            statusLiveData.postValue("invalid-email")
        }else if (!isValidPassword(password)) {
            statusLiveData.postValue("invalid-password")
        }else{
            statusLiveData.postValue("registered")
        }

    }

}