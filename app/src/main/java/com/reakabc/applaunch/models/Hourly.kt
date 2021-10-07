package com.reakabc.applaunch.models

data class Hourly(
    val clouds: Double,
    val dew_poDouble: Double,
    val dt: Double,
    val feels_like: Double,
    val humidity: Double,
    val pop: Double,
    val pressure: Double,
    val rain: Rain,
    val temp: Double,
    val uvi: Float,
    val visibility: Double,
    val weather: List<WeatherXX>,
    val wind_deg: Double,
    val wind_gust: Double,
    val wind_speed: Double
)