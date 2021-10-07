package com.reakabc.applaunch.utils

import android.content.Context
import android.content.res.Configuration

class DayNight {

    companion object{

        fun isNightMode(context: Context): Int {
            return context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        }

    }
}