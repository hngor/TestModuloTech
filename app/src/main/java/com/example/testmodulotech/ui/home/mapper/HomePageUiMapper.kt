package com.example.testmodulotech.ui.home.mapper

import com.example.testmodulotech.domain.state.GetHomeInformationsState
import com.example.testmodulotech.ui.home.model.HomePageUiModel

class HomePageUiMapper {

    fun toUiMapper(state: GetHomeInformationsState): HomePageUiModel {
        return when(state) {
            GetHomeInformationsState.Error -> HomePageUiModel.HomePageError
            is GetHomeInformationsState.HomeInformationsRetrieved -> {
                HomePageUiModel.HomePageData(state.homeInformations.devices)
            }
        }
    }
}