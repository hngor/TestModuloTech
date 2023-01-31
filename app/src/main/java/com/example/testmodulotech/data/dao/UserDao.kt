package com.example.testmodulotech.data.dao

import androidx.room.*
import com.example.testmodulotech.data.model.UserEntity
import com.example.testmodulotech.util.Constants

@Dao
interface UserDao {

    @Query("SELECT * FROM ${Constants.USER_TABLE_NAME}")
    fun getUser(): UserEntity

    @Query("DELETE FROM ${Constants.USER_TABLE_NAME}")
    fun deleteUser()

    @Insert
    fun insertUser(user: UserEntity)

    @Update
    fun updateUser(userEntity: UserEntity)
}