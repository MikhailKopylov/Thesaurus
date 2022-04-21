package ru.amk.tesaurus.presentation

import io.reactivex.observers.DisposableObserver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.amk.tesaurus.entity.AppResponseState
import ru.amk.tesaurus.presentation.interactors.MainHistoryInteractor
import ru.amk.tesaurus.presentation.interactors.MainTranslateInteractor

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

    private fun getObserver(): DisposableObserver<AppResponseState> {
        return object : DisposableObserver<AppResponseState>() {
            override fun onNext(appResponseState: AppResponseState) {
                _translateLiveData.value = appResponseState
            }

            override fun onError(e: Throwable) {
                _translateLiveData.value = AppResponseState.Error(error = e)
            }

            override fun onComplete() {
            }
        }
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
