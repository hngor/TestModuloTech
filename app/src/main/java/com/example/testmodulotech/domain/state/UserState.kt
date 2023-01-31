package com.example.testmodulotech.domain.state

import com.example.testmodulotech.domain.model.User

sealed class UserState {
    data class GetUserSuccess(
        val user: User
    ): UserState()

    object SaveUserSuccess: UserState()
    object SaveUserError: UserState()
}