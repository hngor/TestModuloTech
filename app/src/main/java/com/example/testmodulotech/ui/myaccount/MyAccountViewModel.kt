package com.example.testmodulotech.ui.myaccount

import androidx.lifecycle.viewModelScope
import com.example.testmodulotech.domain.usecase.GetUserUseCase
import com.example.testmodulotech.domain.usecase.SaveUserInformationsUseCase
import com.example.testmodulotech.ui.myaccount.mapper.MyAccountUiMapper
import com.example.testmodulotech.ui.myaccount.model.MyAccountUiModel
import com.example.testmodulotech.util.BaseViewModel
import kotlinx.coroutines.launch

class MyAccountViewModel(
    private val getUserUseCase: GetUserUseCase,
    private val saveUserInformationsUseCase: SaveUserInformationsUseCase,
    private val myAccountUiMapper: MyAccountUiMapper
) :
    BaseViewModel<MyAccountUiModel>() {

    fun getUser() {
        viewModelScope.launch {
            postData(myAccountUiMapper.toUiMapper(getUserUseCase()))
        }
    }

    fun saveUser(
        firstName: String,
        lastName: String,
        birthDate: String,
        postalCode: String,
        streetCode: String,
        street: String,
        city: String,
        country: String
    ) {
        viewModelScope.launch {
            postData(
                myAccountUiMapper.toUiMapper(
                    saveUserInformationsUseCase(
                        firstName = firstName,
                        lastName = lastName,
                        birthDate = birthDate,
                        postalCode = postalCode,
                        streetCode = streetCode,
                        street = street,
                        city = city,
                        country = country
                    )
                )
            )
        }
    }
}