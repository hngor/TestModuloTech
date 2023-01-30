package com.example.testmodulotech.ui.devicesteering.model

import com.example.testmodulotech.domain.model.Device

sealed class DeviceSteeringUiModel {
    data class DeviceData(
        val device: Device
    ): DeviceSteeringUiModel()

    object DeviceUnknown: DeviceSteeringUiModel()
}
