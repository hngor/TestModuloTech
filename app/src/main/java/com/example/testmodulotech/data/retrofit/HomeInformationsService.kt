package com.example.testmodulotech.data.retrofit

import com.example.testmodulotech.data.model.HomeData
import retrofit2.Response
import retrofit2.http.GET

interface HomeInformationsService {

    @GET("data.json")
    suspend fun getHomeInformations(): Response<HomeData>
}