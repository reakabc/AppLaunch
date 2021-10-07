package com.reakabc.applaunch.utils

import android.widget.TextView
import android.widget.TextView.BufferType
import androidx.databinding.BindingAdapter


@BindingAdapter("android:setTextDouble")
fun setTextDouble(view: TextView, value: Double) {
    view.text = value.toString()
}

@BindingAdapter("android:setTextFloat")
fun TextView.setTextFloat(value: Float){
    this.text = value.toString()
}

@BindingAdapter("android:setTimeStamp")
fun TextView.setTimeStamp(value: Long){
    this.text = DateTime.getDate(value)
}

@BindingAdapter("android:setLong")
fun TextView.setLong(value: Long){
    this.text = value.toString()
}
