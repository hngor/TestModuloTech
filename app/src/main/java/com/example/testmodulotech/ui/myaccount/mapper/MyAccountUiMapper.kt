package com.example.testmodulotech.ui.myaccount.mapper

import com.example.testmodulotech.domain.state.UserState
import com.example.testmodulotech.ui.myaccount.model.MyAccountUiModel
import java.text.DateFormat
import java.util.*

class MyAccountUiMapper {

    fun toUiMapper(state: UserState): MyAccountUiModel {
        val dateFormat = DateFormat.getDateInstance(DateFormat.SHORT)

        return when (state) {
            is UserState.GetUserSuccess -> {
                MyAccountUiModel.MyAccountData(
                    firstName = state.user.firstName,
                    lastName = state.user.lastName,
                    city = state.user.city,
                    postalCode = "${state.user.postalCode}",
                    street = state.user.street,
                    streetCode = state.user.streetCode,
                    country = state.user.country,
                    birthDate = dateFormat.format(Date(state.user.birthDate))
                )
            }
            UserState.SaveUserError -> MyAccountUiModel.UserInfoError
            UserState.SaveUserSuccess -> MyAccountUiModel.UserInfosSaved
        }
    }
}