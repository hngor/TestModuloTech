package com.example.testmodulotech.domain.model

data class User(
    val firstName: String,
    val lastName: String,
    val birthDate: Long,
    val city: String,
    val postalCode: Int,
    val street: String,
    val streetCode: String,
    val country: String
)
