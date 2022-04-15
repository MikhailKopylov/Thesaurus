package ru.amk.tesaurus.presentation

import io.reactivex.observers.DisposableObserver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.amk.tesaurus.entity.AppState
import ru.amk.tesaurus.presentation.interactors.MainInteractor

class MainActivityViewModel(
    private val interactor: MainInteractor,
) : BaseViewModel<AppState>() {

    override fun getData(word: String, isOnline: Boolean) {
        _liveData.value = AppState.Loading(null)
        cancelJob()
        job = viewModelCoroutineScope.launch { startInteractor(word, isOnline) }

    }

    private fun getObserver(): DisposableObserver<AppState> {
        return object : DisposableObserver<AppState>() {
            override fun onNext(appState: AppState) {
                _liveData.value = appState
            }

            override fun onError(e: Throwable) {
                _liveData.value = AppState.Error(error = e)
            }

            override fun onComplete() {
            }
        }
    }

    private suspend fun startInteractor(word: String, isOnline: Boolean) =
        withContext(Dispatchers.IO) {
            _liveData.postValue(interactor.getData(word, isOnline))
        }

    override fun handleError(error: Throwable) {
        _liveData.postValue(AppState.Error(error))
    }

    override fun onCleared() {
        _liveData.value = AppState.Success(null)
        super.onCleared()
    }
}
