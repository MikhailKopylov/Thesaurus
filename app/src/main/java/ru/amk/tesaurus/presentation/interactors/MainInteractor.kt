package ru.amk.tesaurus.presentation.interactors

import ru.amk.tesaurus.di.LOCAL_REPOSITORY
import ru.amk.tesaurus.di.REMOTE_REPOSITORY
import ru.amk.tesaurus.entity.AppState
import ru.amk.tesaurus.model.network.data.DataModel
import ru.amk.tesaurus.model.repository.Repository
import javax.inject.Inject
import javax.inject.Named

class MainInteractor @Inject constructor(
    @Named(REMOTE_REPOSITORY) val remoteRepository: Repository<List<DataModel>>,
    @Named(LOCAL_REPOSITORY) val localRepository: Repository<List<DataModel>>
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
