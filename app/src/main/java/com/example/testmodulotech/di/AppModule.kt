package com.example.testmodulotech.di

import androidx.room.Room
import com.example.testmodulotech.data.HomeDataRepository
import com.example.testmodulotech.data.HomeLocalDataSource
import com.example.testmodulotech.data.HomeRemoteDataSource
import com.example.testmodulotech.data.dao.TestModuloTechDB
import com.example.testmodulotech.data.mapper.HomeInformationsMapper
import com.example.testmodulotech.domain.usecase.GetHomeInformationsUseCase
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

    single {
        HomeDataRepository(
            homeRemoteDataSource = get(),
            homeLocalDataSource = get(),
            homeInformationsMapper = get()
        )
    }
    single { GetHomeInformationsUseCase(get()) }

    single { HomePageUiMapper() }

    viewModel {
        HomePageViewModel(getHomeInformationsUseCase = get(), homePageUiMapper = get())
    }

    single {
        Room.databaseBuilder(androidContext(),
            TestModuloTechDB::class.java, Constants.DATABASE_NAME)
            .build()
    }


}