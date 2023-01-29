package com.example.testmodulotech.domain.usecase

import com.example.testmodulotech.data.HomeDataRepository
import com.example.testmodulotech.domain.state.GetHomeInformationsState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetFilteredDeviceListUseCase(private val homeDataRepository: HomeDataRepository) {

    suspend operator fun invoke(productType: String): GetHomeInformationsState {
        return withContext(Dispatchers.IO) {
            if (productType == "Tous") {
                val homeData = homeDataRepository.getHomeData()
                if(homeData != null) {
                    GetHomeInformationsState.HomeInformationsRetrieved(homeData)
                } else {
                    GetHomeInformationsState.Error
                }
            } else {
                val homeData = homeDataRepository.getFilteredDeviceList(productType = productType)
                GetHomeInformationsState.HomeInformationsRetrieved(homeData)
            }
        }

    }
}