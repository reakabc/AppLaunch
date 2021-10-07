package com.reakabc.applaunch.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.reakabc.applaunch.apis.ApiInterface
import com.reakabc.applaunch.models.User
import com.reakabc.applaunch.models.WeatherReport
import com.reakabc.applaunch.utils.SingleLiveData

class WeatherRepository(
    private val apiInterface: ApiInterface,
    private val applicationContext: Context,
) {

    private val weatherReportLiveData = SingleLiveData<WeatherReport>()

    val weatherReport: SingleLiveData<WeatherReport>
        get() = weatherReportLiveData

    suspend fun getReport(){
        try {
            val result = apiInterface.getWeatherReport()
            if (result.body() != null){
                weatherReportLiveData.postValue(result.body())
            }

            Log.d("WEATHER REPORT", result.body().toString())
        }catch (e:Exception){
            Log.d("WEATHER REPORT", e.message.toString())
        }
    }

}