package ru.amk.tesaurus.presentation.interactors

import io.reactivex.Observable
import ru.amk.tesaurus.entity.AppState
import ru.amk.tesaurus.model.network.data.DataModel
import ru.amk.tesaurus.model.repository.Repository

class MainInteractor(
    private val remoteRepository: Repository<List<DataModel>>,
    private val localRepository: Repository<List<DataModel>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean):
        AppState {
        return if (fromRemoteSource) {
            AppState.Success(remoteRepository.getData(word))
        } else {
            AppState.Success(localRepository.getData(word))
        }
    }
}
