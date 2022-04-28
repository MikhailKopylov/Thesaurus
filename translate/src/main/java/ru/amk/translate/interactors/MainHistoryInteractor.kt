package ru.amk.translate.interactors

import ru.amk.core.entity.AppHistoryState
import ru.amk.core.interactors.HistoryInteractor
import ru.amk.core.model.data_source.RoomHistory
import ru.amk.core.model.repository.DataSourceHistory

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
