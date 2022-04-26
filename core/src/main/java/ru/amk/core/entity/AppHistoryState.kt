package ru.amk.core.entity

import ru.amk.core.model.data_source.RoomHistory

sealed class AppHistoryState {

    data class Success(val data: List<RoomHistory>) : AppHistoryState()

    data class Error(val error: Throwable) : AppHistoryState()
}
