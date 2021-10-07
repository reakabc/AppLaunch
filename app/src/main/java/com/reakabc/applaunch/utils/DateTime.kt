package com.reakabc.applaunch.utils


import android.text.format.DateFormat
import java.util.*

class DateTime {

    companion object {

        fun getDate(timestamp: Long): String {
            val calendar = Calendar.getInstance(Locale.ENGLISH)
            calendar.timeInMillis = timestamp * 1000L
            return DateFormat.format("dd-MM-yyyy", calendar).toString()
        }

    }

}