package com.reakabc.applaunch.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.reakabc.applaunch.R
import com.reakabc.applaunch.models.User

class UserAdapter(val list: List<User>, val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(list[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.list_view_user, parent, false))
    }


    override fun getItemCount(): Int {
        return list.size;
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun bind(user: User): Unit {
            itemView.findViewById<TextView>(R.id.tv_name).text = "${user.firstName} ${user.lastName}"
            itemView.findViewById<TextView>(R.id.tv_email).text = user.email
            itemView.setOnClickListener{
                itemView.findNavController().navigate(R.id.action_homeFragment_to_weatherFragment)
//                Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_weatherFragment)
            }
        }

    }

}