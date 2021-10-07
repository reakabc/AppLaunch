package com.reakabc.applaunch.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.reakabc.applaunch.repository.WeatherRepository

class WeatherViewModelFactory(private val weatherRepository: WeatherRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WeatherViewModel(weatherRepository) as T
    }
}