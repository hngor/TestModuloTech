package com.example.testmodulotech.data.mapper

import com.example.testmodulotech.data.model.DeviceData
import com.example.testmodulotech.domain.model.Device
import com.example.testmodulotech.domain.model.Heater
import com.example.testmodulotech.domain.model.Light
import com.example.testmodulotech.domain.model.RollerShutter
import com.example.testmodulotech.util.ProductTypeEnum

class DeviceMapper {
    fun toDomainMapper(deviceData: DeviceData): Device? {
        return when (deviceData.productType) {
            ProductTypeEnum.LIGHT.value -> {
                Light(
                    id = deviceData.id,
                    deviceName = deviceData.deviceName,
                    intensity = deviceData.intensity!!,
                    mode = deviceData.mode!!,
                    productType = ProductTypeEnum.LIGHT
                )
            }
            ProductTypeEnum.ROLLERSHUTTER.value -> {
                RollerShutter(
                    id = deviceData.id,
                    deviceName = deviceData.deviceName,
                    position = deviceData.position!!,
                    productType = ProductTypeEnum.ROLLERSHUTTER
                )
            }
            ProductTypeEnum.HEATER.value -> {
                Heater(
                    id = deviceData.id,
                    deviceName = deviceData.deviceName,
                    mode = deviceData.mode!!,
                    temperature = deviceData.temperature!!,
                    productType = ProductTypeEnum.HEATER
                )
            }
            else -> null
        }
    }
}