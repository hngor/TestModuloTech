package com.example.testmodulotech.data

import com.example.testmodulotech.data.mapper.DeviceMapper
import com.example.testmodulotech.data.mapper.HomeInformationsMapper
import com.example.testmodulotech.data.model.DeviceData
import com.example.testmodulotech.data.model.HomeData
import com.example.testmodulotech.data.model.UserData
import com.example.testmodulotech.data.model.UserEntity
import com.example.testmodulotech.domain.model.Device
import com.example.testmodulotech.domain.model.HomeInformations

class HomeDataRepository(
    private val homeRemoteDataSource: HomeRemoteDataSource,
    private val homeLocalDataSource: HomeLocalDataSource,
    private val homeInformationsMapper: HomeInformationsMapper,
    private val deviceMapper: DeviceMapper
) {

    suspend fun getHomeData(): HomeInformations?{
        val devices = mutableListOf<DeviceData>()
        val homeRemoteData = homeRemoteDataSource.getHomeData()
        homeRemoteData?.let {
            homeLocalDataSource.clear()
            //get device list
            homeRemoteData.devices.forEach { device ->
                devices.add(device)

                //store device in local database
                homeLocalDataSource.addDevice(device)
            }

            //store user data in local database
            homeLocalDataSource.addUser(homeRemoteData.user)

            return homeInformationsMapper.toDomainMapper(homeRemoteData)
        }

        return null
    }

    suspend fun getUser(): UserData {
        return homeLocalDataSource.getUser()
    }

    suspend fun saveUser(userEntity: UserEntity) {
        homeLocalDataSource.updateUser(userEntity)
    }

    suspend fun getDevice(deviceId: Int): Device? {
        val device = homeLocalDataSource.getDevice(deviceId = deviceId)
        return deviceMapper.toDomainMapper(device)
    }

    suspend fun getFilteredDeviceList(productType: String): HomeInformations {
        val devices = homeLocalDataSource.getFilteredDeviceList(productType = productType)
        val homeData = HomeData(devices, homeLocalDataSource.getUser())
        return homeInformationsMapper.toDomainMapper(homeData)
    }

    suspend fun deleteDevice(deviceId: Int): HomeInformations {
        homeLocalDataSource.deleteDevice(deviceId = deviceId)
        val devices = homeLocalDataSource.getAllDevices()
        val homeData = HomeData(devices, homeLocalDataSource.getUser())
        return homeInformationsMapper.toDomainMapper(homeData)
    }

}