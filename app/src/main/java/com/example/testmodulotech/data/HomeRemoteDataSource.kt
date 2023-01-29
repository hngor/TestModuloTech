package com.example.testmodulotech.data

import com.example.testmodulotech.data.model.HomeData
import com.example.testmodulotech.data.retrofit.HomeInformationsService
import com.example.testmodulotech.data.retrofit.RetrofitClient

class HomeRemoteDataSource {
    private val retrofit = RetrofitClient.getInstance()
    private val api = retrofit.create(HomeInformationsService::class.java)

    suspend fun getHomeData(): HomeData? {
        val response = api.getHomeInformations()

        return response.body()
    }
}