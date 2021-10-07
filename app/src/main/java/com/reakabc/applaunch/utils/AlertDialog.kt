package com.reakabc.applaunch.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.CountDownTimer
import android.view.Gravity
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import com.reakabc.applaunch.databinding.DialogAlertBinding

class AlertDialog {

    companion object{

        fun showAlert(context: Context, heading: String, message: String) {

            val dialog = Dialog(context)
            val binding: DialogAlertBinding = DataBindingUtil.inflate(
                LayoutInflater.from(context), com.reakabc.applaunch.R.layout.dialog_alert,
                null,
                false)
            binding.heading = heading
            binding.message = message
            dialog.setContentView(binding.root)
            dialog.setCancelable(true)

            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.window!!.setGravity(Gravity.CENTER)
            dialog.window!!.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)

            val lp = WindowManager.LayoutParams()
            lp.copyFrom(dialog.window!!.attributes)
            lp.width = WindowManager.LayoutParams.WRAP_CONTENT
            dialog.window!!.attributes = lp

            dialog.show()
            object : CountDownTimer(2000, 2000) {
                override fun onTick(millisUntilFinished: Long) {

                }
                override fun onFinish() {
                    dialog.dismiss()
                }
            }.start()
        }

    }
}