package com.example.testmodulotech.ui.home

import androidx.lifecycle.viewModelScope
import com.example.testmodulotech.domain.usecase.DeleteDeviceUseCase
import com.example.testmodulotech.domain.usecase.GetFilteredDeviceListUseCase
import com.example.testmodulotech.domain.usecase.GetHomeInformationsUseCase
import com.example.testmodulotech.ui.home.mapper.HomePageUiMapper
import com.example.testmodulotech.ui.home.model.HomePageUiModel
import com.example.testmodulotech.util.BaseViewModel
import kotlinx.coroutines.launch

class HomePageViewModel(
    private val getHomeInformationsUseCase: GetHomeInformationsUseCase,
    private val getFilteredDeviceListUseCase: GetFilteredDeviceListUseCase,
    private val deleteDeviceUseCase: DeleteDeviceUseCase,
    private val homePageUiMapper: HomePageUiMapper
) : BaseViewModel<HomePageUiModel>() {

    fun getHomeData() {
        viewModelScope.launch {
            postData(HomePageUiModel.Loading)
            val homeData = getHomeInformationsUseCase()
            postData(homePageUiMapper.toUiMapper(homeData))
        }
    }

    fun getFilteredDeviceList(productType: String) {
        viewModelScope.launch {
            postData(HomePageUiModel.Loading)
            val homeData = getFilteredDeviceListUseCase(productType = productType)
            postData(homePageUiMapper.toUiMapper(homeData))
        }
    }

    fun deleteDevice(deviceId: Int) {
        viewModelScope.launch {
            val homeData = deleteDeviceUseCase(deviceId)
            postData((homePageUiMapper.toUiMapper(homeData)))
        }
    }
}