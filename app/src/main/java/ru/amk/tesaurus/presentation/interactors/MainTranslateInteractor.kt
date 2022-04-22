package ru.amk.tesaurus.presentation.interactors

import ru.amk.tesaurus.entity.AppResponseState
import ru.amk.tesaurus.model.network.data.DataModel
import ru.amk.tesaurus.model.repository.Repository

class MainTranslateInteractor(
    private val remoteRepository: Repository<List<DataModel>>,
    private val localRepository: Repository<List<DataModel>>,
) : TranslateInteractor<AppResponseState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean):
        AppResponseState {
        return if (fromRemoteSource) {
            AppResponseState.Success(remoteRepository.getData(word))
        } else {
            AppResponseState.Success(localRepository.getData(word))
        }
    }
}
