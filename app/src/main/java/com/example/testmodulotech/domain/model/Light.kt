package com.example.testmodulotech.domain.model

import com.example.testmodulotech.util.ProductTypeEnum

class Light(
    override val id: Int,
    override val deviceName: String,
    override val productType: ProductTypeEnum,
    val intensity: Int,
    val mode: String,
): Device(id, deviceName, productType)
