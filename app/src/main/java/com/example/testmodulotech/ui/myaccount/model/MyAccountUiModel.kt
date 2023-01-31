package com.example.testmodulotech.ui.myaccount.model

sealed class MyAccountUiModel {
    data class MyAccountData(
        val firstName: String,
        val lastName: String,
        val city: String,
        val postalCode: String,
        val street: String,
        val streetCode: String,
        val country: String,
        val birthDate: String
    ): MyAccountUiModel()

    object UserInfosSaved: MyAccountUiModel()
    object UserInfoError: MyAccountUiModel()
}