package com.reakabc.applaunch.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.reakabc.applaunch.models.User

@Dao
interface UserDAO {

    @Insert
    suspend  fun insertUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("SELECT * FROM users")
    suspend fun getUser(): List<User>

}