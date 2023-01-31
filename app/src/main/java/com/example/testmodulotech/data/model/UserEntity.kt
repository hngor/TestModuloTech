package com.example.testmodulotech.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.testmodulotech.util.Constants

@Entity(tableName = Constants.USER_TABLE_NAME)
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val firstName: String,
    val lastName: String,
    val birthDate: Long,
    val city: String,
    val postalCode: Int,
    val street: String,
    val streetCode: String,
    val country: String
)