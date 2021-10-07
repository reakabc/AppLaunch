package com.reakabc.applaunch.models

data class Current(
    val clouds: Double,
    val dew_poDouble: Double,
    val dt: Double,
    val feels_like: Double,
    val humidity: Double,
    val pressure: Double,
    val sunrise: Double,
    val sunset: Double,
    val temp: Double,
    val uvi: Float,
    val visibility: Double,
    val weather: List<Weather>,
    val wind_deg: Double,
    val wind_speed: Double
)