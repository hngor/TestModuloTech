package com.example.testmodulotech.ui.devicesteering

import androidx.lifecycle.viewModelScope
import com.example.testmodulotech.domain.usecase.GetDeviceUseCase
import com.example.testmodulotech.ui.devicesteering.mapper.DeviceSteeringUiMapper
import com.example.testmodulotech.ui.devicesteering.model.DeviceSteeringUiModel
import com.example.testmodulotech.util.BaseViewModel
import kotlinx.coroutines.launch

class DeviceSteeringViewModel(
    private val getDeviceUseCase: GetDeviceUseCase,
    private val deviceSteeringUiMapper: DeviceSteeringUiMapper
): BaseViewModel<DeviceSteeringUiModel>() {

    fun getDevice(deviceId: Int) {
        viewModelScope.launch {
            val state = getDeviceUseCase(deviceId = deviceId)
            postData(deviceSteeringUiMapper.toUiMapper(state))
        }
    }
}