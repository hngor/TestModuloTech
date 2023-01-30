package com.example.testmodulotech.di

import androidx.room.Room
import com.example.testmodulotech.data.HomeDataRepository
import com.example.testmodulotech.data.HomeLocalDataSource
import com.example.testmodulotech.data.HomeRemoteDataSource
import com.example.testmodulotech.data.dao.TestModuloTechDB
import com.example.testmodulotech.data.mapper.DeviceMapper
import com.example.testmodulotech.data.mapper.HomeInformationsMapper
import com.example.testmodulotech.domain.usecase.DeleteDeviceUseCase
import com.example.testmodulotech.domain.usecase.GetDeviceUseCase
import com.example.testmodulotech.domain.usecase.GetFilteredDeviceListUseCase
import com.example.testmodulotech.domain.usecase.GetHomeInformationsUseCase
import com.example.testmodulotech.ui.devicesteering.DeviceSteeringViewModel
import com.example.testmodulotech.ui.devicesteering.mapper.DeviceSteeringUiMapper
import com.example.testmodulotech.ui.home.HomePageViewModel
import com.example.testmodulotech.ui.home.mapper.HomePageUiMapper
import com.example.testmodulotech.util.Constants
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { HomeRemoteDataSource() }
    single { HomeLocalDataSource(db = get()) }

    single { HomeInformationsMapper() }
    single { DeviceMapper() }

    single {
        HomeDataRepository(
            homeRemoteDataSource = get(),
            homeLocalDataSource = get(),
            homeInformationsMapper = get(),
            deviceMapper = get()
        )
    }
    single { GetHomeInformationsUseCase(homeDataRepository = get()) }
    single { GetFilteredDeviceListUseCase(homeDataRepository = get()) }
    single { DeleteDeviceUseCase(homeDataRepository = get()) }
    single { GetDeviceUseCase(homeDataRepository = get()) }

    single { HomePageUiMapper() }
    single { DeviceSteeringUiMapper() }

    viewModel {
        HomePageViewModel(
            getHomeInformationsUseCase = get(),
            getFilteredDeviceListUseCase = get(),
            deleteDeviceUseCase = get(),
            homePageUiMapper = get()
        )
    }

    viewModel {
        DeviceSteeringViewModel(getDeviceUseCase = get(), deviceSteeringUiMapper = get())
    }

    single {
        Room.databaseBuilder(
            androidContext(),
            TestModuloTechDB::class.java, Constants.DATABASE_NAME
        )
            .build()
    }


}