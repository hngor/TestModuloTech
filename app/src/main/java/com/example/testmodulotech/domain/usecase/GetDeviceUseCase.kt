package com.example.testmodulotech.domain.usecase

import com.example.testmodulotech.data.HomeDataRepository
import com.example.testmodulotech.domain.state.GetDeviceState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetDeviceUseCase(private val homeDataRepository: HomeDataRepository) {

    suspend operator fun invoke(deviceId: Int): GetDeviceState {
        return withContext(Dispatchers.IO) {
            val device = homeDataRepository.getDevice(deviceId = deviceId)
            if (device != null) {
                GetDeviceState.GetDeviceSuccess(device)
            } else {
                GetDeviceState.GetDeviceFailure
            }
        }
    }
}