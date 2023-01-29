package com.example.testmodulotech.domain.model

import com.example.testmodulotech.util.ProductTypeEnum

open class Device(
    open val id: Int,
    open val deviceName: String,
    open val productType: ProductTypeEnum
)