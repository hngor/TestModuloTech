package com.example.testmodulotech.data.dao

import androidx.room.*
import com.example.testmodulotech.data.model.DeviceData
import com.example.testmodulotech.util.Constants

@Dao
interface DevicesDao {

    @Query("SELECT * FROM ${Constants.DEVICES_TABLE_NAME}")
    fun getAllDevices(): List<DeviceData>

    @Query("SELECT * FROM ${Constants.DEVICES_TABLE_NAME} WHERE productType=:productTypeName")
    fun getDevicesFromProductType(productTypeName: String): List<DeviceData>

    @Query("SELECT * FROM ${Constants.DEVICES_TABLE_NAME} WHERE id=:deviceId")
    fun getDevice(deviceId: Int): DeviceData

    @Query("DELETE FROM ${Constants.DEVICES_TABLE_NAME}")
    fun clearTable()

    @Insert
    fun insertDevice(device: DeviceData)

    @Update
    fun updateDevice(device: DeviceData)

    @Delete
    fun deleteDevice(device: DeviceData)
}