package ru.amk.tesaurus.entity

import ru.amk.tesaurus.model.dataSource.RoomHistory

sealed class AppHistoryState {

    data class Success(val data: List<RoomHistory>) : AppHistoryState()

    data class Error(val error: Throwable) : AppHistoryState()
}
