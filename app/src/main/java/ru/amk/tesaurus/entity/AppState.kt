package ru.amk.tesaurus.entity

import ru.amk.tesaurus.model.network.data.DataModel

sealed class AppState {

    data class Success(val data: List<DataModel>?) : AppState()

    data class Error(val error: Throwable) : AppState()

    data class Loading(val progress: Int?) : AppState()
}
