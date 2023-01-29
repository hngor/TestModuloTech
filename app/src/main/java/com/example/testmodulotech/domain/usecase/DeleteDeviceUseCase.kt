package com.example.testmodulotech.domain.usecase

import com.example.testmodulotech.data.HomeDataRepository
import com.example.testmodulotech.domain.state.GetHomeInformationsState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DeleteDeviceUseCase(private val homeDataRepository: HomeDataRepository) {

    suspend operator fun invoke(deviceId: Int): GetHomeInformationsState {
        return withContext(Dispatchers.IO) {
            val homeData = homeDataRepository.deleteDevice(deviceId = deviceId)
            GetHomeInformationsState.HomeInformationsRetrieved(homeData)
        }
    }
}