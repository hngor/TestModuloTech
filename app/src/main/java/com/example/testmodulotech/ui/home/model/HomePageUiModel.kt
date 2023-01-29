package com.example.testmodulotech.ui.home.model

import com.example.testmodulotech.domain.model.Device

sealed class HomePageUiModel {
    data class HomePageData(
        val deviceList: List<Device>
    ): HomePageUiModel()

    object HomePageError: HomePageUiModel()
    object Loading: HomePageUiModel()
}