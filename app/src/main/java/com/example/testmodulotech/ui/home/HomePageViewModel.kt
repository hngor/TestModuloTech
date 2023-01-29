package com.example.testmodulotech.ui.home

import androidx.lifecycle.viewModelScope
import com.example.testmodulotech.domain.usecase.GetHomeInformationsUseCase
import com.example.testmodulotech.ui.home.mapper.HomePageUiMapper
import com.example.testmodulotech.ui.home.model.HomePageUiModel
import com.example.testmodulotech.util.BaseViewModel
import kotlinx.coroutines.launch

class HomePageViewModel(
    private val getHomeInformationsUseCase: GetHomeInformationsUseCase,
    private val homePageUiMapper: HomePageUiMapper
) : BaseViewModel<HomePageUiModel>() {

    fun getHomeData() {
        viewModelScope.launch {
            postData(HomePageUiModel.Loading)
            val homeData = getHomeInformationsUseCase()
            postData(homePageUiMapper.toUiMapper(homeData))
        }
    }
}