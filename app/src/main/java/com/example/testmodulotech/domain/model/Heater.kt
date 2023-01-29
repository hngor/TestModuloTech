package com.example.testmodulotech.domain.model

import com.example.testmodulotech.util.ProductTypeEnum

class Heater(
    override val id: Int,
    override val deviceName: String,
    override val productType: ProductTypeEnum,
    val mode: String,
    val temperature: Int
) : Device(id, deviceName, productType)


