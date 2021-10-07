package com.reakabc.applaunch.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reakabc.applaunch.models.User
import com.reakabc.applaunch.models.WeatherReport
import com.reakabc.applaunch.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class WeatherViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {

    val report: LiveData<WeatherReport>
    get() = weatherRepository.weatherReport

    fun getReport(){
        viewModelScope.launch(Dispatchers.IO) {
            weatherRepository.getReport()
        }
    }

}