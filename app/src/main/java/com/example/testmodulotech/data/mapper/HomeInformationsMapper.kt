package com.example.testmodulotech.data.mapper

import com.example.testmodulotech.data.model.HomeData
import com.example.testmodulotech.domain.model.*
import com.example.testmodulotech.util.ProductTypeEnum

class HomeInformationsMapper {

    fun toDomainMapper(data: HomeData): HomeInformations {
        val devices = mutableListOf<Device>()
        data.devices.forEach { device ->
            when (device.productType) {
                ProductTypeEnum.LIGHT.value -> {
                    val lightDevice = Light(
                        id = device.id,
                        deviceName = device.deviceName,
                        intensity = device.intensity!!,
                        mode = device.mode!!,
                        productType = ProductTypeEnum.LIGHT
                    )
                    devices.add(lightDevice)
                }
                ProductTypeEnum.ROLLERSHUTTER.value -> {
                    val rollerShutterDevice = RollerShutter(
                        id = device.id,
                        deviceName = device.deviceName,
                        position = device.position!!,
                        productType = ProductTypeEnum.ROLLERSHUTTER
                    )
                    devices.add(rollerShutterDevice)
                }
                ProductTypeEnum.HEATER.value -> {
                    val heaterDevice = Heater(
                        id = device.id,
                        deviceName = device.deviceName,
                        mode = device.mode!!,
                        temperature = device.temperature!!,
                        productType = ProductTypeEnum.HEATER
                    )
                    devices.add(heaterDevice)
                }
            }
        }

        return HomeInformations(devices)
    }
}