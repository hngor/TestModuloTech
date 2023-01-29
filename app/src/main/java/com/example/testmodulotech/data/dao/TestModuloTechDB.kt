package com.example.testmodulotech.data.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testmodulotech.data.model.DeviceData
import com.example.testmodulotech.util.Constants

@Database(entities = [(DeviceData::class)], version = Constants.DB_VERSION, exportSchema = true)
abstract class TestModuloTechDB: RoomDatabase() {
    abstract fun devicesDao(): DevicesDao

}