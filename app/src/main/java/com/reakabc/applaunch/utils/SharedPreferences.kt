package com.reakabc.applaunch.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.reakabc.applaunch.R

class SharedPreferences(val context: Context) {

    private var preferences: SharedPreferences =
        context.getSharedPreferences(context.getString(R.string.preferences), MODE_PRIVATE)

    fun putUserId(userId: Int) {
        val editor: SharedPreferences.Editor = preferences.edit()
        editor.putInt(context.resources.getString(R.string.current_user_id), userId)
        editor.apply()
    }

    fun getUserId(): Int {
        return preferences.getInt(context.resources.getString(R.string.current_user_id), 0)
    }

    fun putUserEmail(name: String) {
        val editor: SharedPreferences.Editor = preferences.edit()
        editor.putString(context.resources.getString(R.string.current_user_name), name)
        editor.apply()
    }

    fun getUserName(): String? {
        return preferences.getString(context.resources.getString(R.string.current_user_name), "")
    }

    fun putIsUserLoggedIn(loggedIn: Boolean) {
        val editor: SharedPreferences.Editor = preferences.edit()
        editor.putBoolean(context.resources.getString(R.string.current_user_loggedIn), loggedIn)
        editor.apply()
    }

    fun isUserLoggedIn(): Boolean {
        return preferences.getBoolean(context.resources.getString(R.string.current_user_loggedIn), false)
    }

}