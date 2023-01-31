package com.example.testmodulotech.util

import kotlinx.coroutines.processNextEventInCurrentThread

class Constants {
    companion object {

        val PRODUCT_TYPE_FILTER = listOf(
            "Tous",
            ProductTypeEnum.HEATER.value,
            ProductTypeEnum.ROLLERSHUTTER.value,
            ProductTypeEnum.LIGHT.value
        )

        const val MODE_ON = "ON"
        const val MODE_OFF = "OFF"

        const val DATABASE_NAME = "testModuloTechDB"

        //database version
        const val DB_VERSION = 1

        //database table names
        const val DEVICES_TABLE_NAME = "devices"
        const val USER_TABLE_NAME = "user"
    }
}