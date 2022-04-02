package ru.amk.tesaurus.presentation.interactors

import io.reactivex.Observable
import ru.amk.tesaurus.entity.AppState
import ru.amk.tesaurus.model.network.data.DataModel
import ru.amk.tesaurus.model.repository.Repository

class MainInteractor(
    private val remoteRepository: Repository<List<DataModel>>,
    private val localRepository: Repository<List<DataModel>>
) : Interactor<AppState> {

    override fun getData(word: String, fromRemoteSource: Boolean):
        Observable<AppState> {
        return if (fromRemoteSource) {
            remoteRepository.getData(word).map { AppState.Success(it) }
        } else {
            localRepository.getData(word).map { AppState.Success(it) }
        }
    }
}
