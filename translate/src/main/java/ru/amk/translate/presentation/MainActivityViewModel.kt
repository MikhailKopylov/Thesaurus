package ru.amk.translate.presentation

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.amk.core.entity.AppResponseState
import ru.amk.translate.interactors.MainHistoryInteractor
import ru.amk.translate.interactors.MainTranslateInteractor

class MainActivityViewModel(
    private val translateInteractor: MainTranslateInteractor,
    private val historyInteractor: MainHistoryInteractor,
) : BaseViewModel<AppResponseState>() {

    override fun getData() {
        cancelJob()
        historyJob = viewModelCoroutineScope.launch {
            starHistoryInteractor()
        }
    }

    override fun requestData(word: String, isOnline: Boolean) {
        _translateLiveData.value = AppResponseState.Loading(null)
        cancelJob()
        translateJob = viewModelCoroutineScope.launch { starTranslatetInteractor(word, isOnline) }
    }

    private suspend fun starHistoryInteractor() =
        withContext(Dispatchers.IO) {
            _historyLiveData.postValue(historyInteractor.getData())
        }

    private suspend fun starTranslatetInteractor(word: String, isOnline: Boolean) =
        withContext(Dispatchers.IO) {
            _translateLiveData.postValue(translateInteractor.getData(word, isOnline))
        }

    override fun handleError(error: Throwable) {
        _translateLiveData.postValue(AppResponseState.Error(error))
    }

    override fun onCleared() {
        _translateLiveData.value = AppResponseState.Success(null)
        super.onCleared()
    }

    override fun saveWordHistory(word: String) {
        viewModelCoroutineScope.launch {
            withContext(Dispatchers.IO) {
                historyInteractor.saveData(word)
            }
        }
    }

}
