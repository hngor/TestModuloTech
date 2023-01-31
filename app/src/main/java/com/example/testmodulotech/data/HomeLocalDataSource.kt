package com.example.testmodulotech.data

import com.example.testmodulotech.data.dao.TestModuloTechDB
import com.example.testmodulotech.data.model.AddressData
import com.example.testmodulotech.data.model.DeviceData
import com.example.testmodulotech.data.model.UserData
import com.example.testmodulotech.data.model.UserEntity

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

    fun addUser(user: UserData) {
        val entity = UserEntity(
            id = 1,
            firstName = user.firstName,
            lastName = user.lastName,
            birthDate = user.birthDate,
            city = user.address.city,
            postalCode = user.address.postalCode,
            street = user.address.street,
            streetCode = user.address.streetCode,
            country = user.address.country
        )
        db.userDao().insertUser(entity)
    }

    fun getUser(): UserData {
        val user = db.userDao().getUser()
        val address = AddressData(
            city = user.city,
            postalCode = user.postalCode,
            street = user.street,
            streetCode = user.streetCode,
            country = user.country
        )

        return UserData(
            firstName = user.firstName,
            lastName = user.lastName,
            birthDate = user.birthDate,
            address = address
        )
    }

    fun updateUser(user: UserEntity) {
        db.userDao().updateUser(user)
    }

    fun clear() {
        db.devicesDao().clearTable()
        db.userDao().deleteUser()
    }
}