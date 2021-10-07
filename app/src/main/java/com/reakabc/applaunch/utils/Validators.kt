package com.reakabc.applaunch.utils

import android.text.TextUtils
import android.util.Patterns
import java.util.regex.Pattern

class Validators {

    companion object{

        fun isValidEmail(target: CharSequence?): Boolean {

            val pattern = Pattern.compile(
                "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                        "\\@" +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                        "(" +
                        "\\." +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                        ")+"
            )

            return !TextUtils.isEmpty(target) && pattern.matcher(target).matches()
            /*return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()*/
        }

        fun isValidName(target: CharSequence?): Boolean {
            return !TextUtils.isEmpty(target) && target.toString().length > 3
        }

        fun isValidPassword(s: String?): Boolean {

            val passwordPattern: Pattern = Pattern.compile(
                "[a-zA-Z0-9\\!\\@\\#\\$]{8,24}"
            )
            return !TextUtils.isEmpty(s) && passwordPattern.matcher(s).matches()
        }

    }

}