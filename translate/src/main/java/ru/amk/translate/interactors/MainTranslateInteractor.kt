package ru.amk.translate.interactors

import ru.amk.core.entity.AppResponseState
import ru.amk.core.interactors.TranslateInteractor
import ru.amk.core.model.network.data.DataModel
import ru.amk.core.model.repository.Repository

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
