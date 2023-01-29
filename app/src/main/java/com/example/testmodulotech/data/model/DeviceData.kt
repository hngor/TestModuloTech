package com.example.testmodulotech.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.testmodulotech.util.Constants

@Entity(tableName = Constants.DEVICES_TABLE_NAME)
data class DeviceData(
    @PrimaryKey val id: Int,
    val deviceName: String,
    val intensity: Int?,
    val position: Int?,
    val temperature: Int?,
    val mode: String?,
    val productType: String
)
