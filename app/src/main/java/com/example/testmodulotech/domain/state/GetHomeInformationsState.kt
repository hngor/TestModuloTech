package com.example.testmodulotech.domain.state

import com.example.testmodulotech.domain.model.HomeInformations

sealed class GetHomeInformationsState {
    data class HomeInformationsRetrieved(
        val homeInformations: HomeInformations
    ): GetHomeInformationsState()

    object Error: GetHomeInformationsState()
}
