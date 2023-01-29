package com.example.testmodulotech.data

import com.example.testmodulotech.data.mapper.HomeInformationsMapper
import com.example.testmodulotech.data.model.DeviceData
import com.example.testmodulotech.data.model.HomeData
import com.example.testmodulotech.domain.model.HomeInformations

class HomeDataRepository(
    private val homeRemoteDataSource: HomeRemoteDataSource,
    private val homeLocalDataSource: HomeLocalDataSource,
    private val homeInformationsMapper: HomeInformationsMapper
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
            return homeInformationsMapper.toDomainMapper(homeRemoteData)
        }

        return null
    }

    suspend fun getFilteredDeviceList(productType: String): HomeInformations {
        val devices = homeLocalDataSource.getFilteredDeviceList(productType = productType)
        val homeData = HomeData(devices)
        return homeInformationsMapper.toDomainMapper(homeData)
    }

}