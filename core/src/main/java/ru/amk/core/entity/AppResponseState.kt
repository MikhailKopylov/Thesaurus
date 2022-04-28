package ru.amk.core.entity

import ru.amk.core.model.network.data.DataModel

sealed class AppResponseState {

    data class Success(val data: List<DataModel>?) : AppResponseState()

    data class Error(val error: Throwable) : AppResponseState()

    data class Loading(val progress: Int?) : AppResponseState()
}
