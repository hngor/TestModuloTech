package com.example.testmodulotech.ui.devicesteering.mapper

import com.example.testmodulotech.domain.state.GetDeviceState
import com.example.testmodulotech.ui.devicesteering.model.DeviceSteeringUiModel

class DeviceSteeringUiMapper {

    fun toUiMapper(state: GetDeviceState): DeviceSteeringUiModel {
        return when (state) {
            GetDeviceState.GetDeviceFailure -> DeviceSteeringUiModel.DeviceUnknown
            is GetDeviceState.GetDeviceSuccess -> {

                DeviceSteeringUiModel.DeviceData(state.device)
            }
        }
    }
}