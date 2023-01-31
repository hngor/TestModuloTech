package com.example.testmodulotech.domain.usecase

import com.example.testmodulotech.data.HomeDataRepository
import com.example.testmodulotech.domain.model.User
import com.example.testmodulotech.domain.state.UserState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetUserUseCase(private val homeDataRepository: HomeDataRepository) {

    suspend operator fun invoke(): UserState {
        return withContext(Dispatchers.IO) {
            val userData = homeDataRepository.getUser()

            UserState.GetUserSuccess(
                User(
                    firstName = userData.firstName,
                    lastName = userData.lastName,
                    birthDate = userData.birthDate,
                    city = userData.address.city,
                    postalCode = userData.address.postalCode,
                    streetCode = userData.address.streetCode,
                    street = userData.address.street,
                    country = userData.address.country
                )
            )
        }
    }
}