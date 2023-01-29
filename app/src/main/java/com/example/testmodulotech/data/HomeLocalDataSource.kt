package com.example.testmodulotech.data

import com.example.testmodulotech.data.dao.TestModuloTechDB
import com.example.testmodulotech.data.model.DeviceData

class HomeLocalDataSource(private val db: TestModuloTechDB) {

    fun getAllDevices(): List<DeviceData> {
        return db.devicesDao().getAllDevices()
    }

    fun getDevice(deviceId: Int): DeviceData {
        return db.devicesDao().getDevice(deviceId = deviceId)
    }

    fun getFilteredDeviceList(productType: String): List<DeviceData> {
        return db.devicesDao().getDevicesFromProductType(productTypeName = productType)
    }

    fun addDevice(device: DeviceData) {
        db.devicesDao().insertDevice(device)
    }

    fun updateDevice(device: DeviceData) {
        db.devicesDao().updateDevice(device)
    }

    fun deleteDevice(deviceId: Int) {
        val device = db.devicesDao().getDevice(deviceId = deviceId)
        db.devicesDao().deleteDevice(device)
    }

    fun clear() {
        db.devicesDao().clearTable()
    }
}