package com.example.testmodulotech.domain.state

import com.example.testmodulotech.domain.model.Device

sealed class GetDeviceState {
    data class GetDeviceSuccess(val device: Device) : GetDeviceState()
    object GetDeviceFailure : GetDeviceState()
}
