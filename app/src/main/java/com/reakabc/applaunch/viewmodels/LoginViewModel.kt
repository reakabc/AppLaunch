package com.reakabc.applaunch.viewmodels

import android.text.TextUtils
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reakabc.applaunch.repository.UserRepository
import com.reakabc.applaunch.utils.Validators.Companion.isValidEmail
import com.reakabc.applaunch.utils.Validators.Companion.isValidPassword
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.regex.Pattern


class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val statusLiveData = MutableLiveData<String>()

    val status: MutableLiveData<String>
        get() = statusLiveData

    fun login(email:String, password:String){

        if (!isValidEmail(email)) {
            statusLiveData.postValue("invalid-email")
        }else if (!isValidPassword(password)) {
            statusLiveData.postValue("invalid-password")
        }else{
            statusLiveData.postValue("loggedin")
        }

    }
}