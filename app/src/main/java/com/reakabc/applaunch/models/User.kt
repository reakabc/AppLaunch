package com.reakabc.applaunch.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey
    val id:Int?,
    val firstName:String,
    val lastName:String,
    val email:String,
)


/*database.contactDao().getContact().observe(this, {
            Log.d("ReaK AbC", it.toString())
        })
        Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show()*/

/*database = ContactDatabase.getDatabase(this)

        GlobalScope.launch {

            database.contactDao().insertContact(Contact(0, "ReaK AbC", "6200097353", Date(), 1))

        }*/