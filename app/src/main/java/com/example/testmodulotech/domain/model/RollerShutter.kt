package com.example.testmodulotech.domain.model

import com.example.testmodulotech.util.ProductTypeEnum

class RollerShutter(
    override val id: Int,
    override val deviceName: String,
    override val productType: ProductTypeEnum,
    val position: Int,
): Device(id, deviceName, productType)
