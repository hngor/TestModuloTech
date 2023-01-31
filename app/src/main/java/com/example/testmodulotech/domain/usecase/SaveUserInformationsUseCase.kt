package com.example.testmodulotech.domain.usecase

import com.example.testmodulotech.data.HomeDataRepository
import com.example.testmodulotech.data.model.UserEntity
import com.example.testmodulotech.domain.state.UserState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import java.security.Timestamp
import java.text.DateFormat

class SaveUserInformationsUseCase(private val homeDataRepository: HomeDataRepository) {

    suspend operator fun invoke(
        firstName: String,
        lastName: String,
        birthDate: String,
        postalCode: String,
        streetCode: String,
        street: String,
        city: String,
        country: String
    ): UserState {
        return withContext(Dispatchers.IO) {
            //convert date to timestamp
            val dateFormatter = DateFormat.getDateInstance(DateFormat.SHORT)
            val date = dateFormatter.parse(birthDate)

            if(date == null || postalCode.toIntOrNull() == null) {
                UserState.SaveUserError
            } else {
                val userEntity = UserEntity(
                    id = 1,
                    firstName = firstName,
                    lastName = lastName,
                    birthDate = date.time,
                    city = city,
                    postalCode = postalCode.toInt(),
                    street = street,
                    streetCode = streetCode,
                    country = country
                )
                homeDataRepository.saveUser(userEntity)

                UserState.SaveUserSuccess
            }
        }

    }
}