package com.example.testmodulotech.domain.usecase

import com.example.testmodulotech.data.HomeDataRepository
import com.example.testmodulotech.domain.state.GetHomeInformationsState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetHomeInformationsUseCase(private val homeDataRepository: HomeDataRepository) {

    suspend operator fun invoke(): GetHomeInformationsState {
        return withContext(Dispatchers.IO) {
            val homeData = homeDataRepository.getHomeData()

            if(homeData != null) {
                GetHomeInformationsState.HomeInformationsRetrieved(homeData)
            } else {
                GetHomeInformationsState.Error
            }
        }
    }
}