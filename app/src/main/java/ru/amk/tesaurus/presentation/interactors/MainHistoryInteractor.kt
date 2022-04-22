package ru.amk.tesaurus.presentation.interactors

import ru.amk.tesaurus.entity.AppHistoryState
import ru.amk.tesaurus.model.dataSource.RoomHistory
import ru.amk.tesaurus.model.repository.DataSourceHistory

class MainHistoryInteractor(
    private val dataSourceHistory: DataSourceHistory<List<RoomHistory>>
) : HistoryInteractor<AppHistoryState> {

    override suspend fun getData(): AppHistoryState {
        return AppHistoryState.Success(dataSourceHistory.getData())
    }

    override suspend fun saveData(data: String) {
        dataSourceHistory.saveData(data)
    }
}
