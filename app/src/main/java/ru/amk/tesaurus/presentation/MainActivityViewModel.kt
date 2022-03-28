package ru.amk.tesaurus.presentation

import androidx.lifecycle.LiveData
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import ru.amk.tesaurus.entity.AppState
import ru.amk.tesaurus.presentation.interactors.MainInteractor
import ru.amk.tesaurus.rx.SchedulerProvider
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val interactor: MainInteractor,
    private val compositeDisposable: CompositeDisposable,
    private val schedulerProvider: SchedulerProvider,
) : BaseViewModel<AppState>() {

    override fun getData(word: String, isOnline: Boolean): LiveData<AppState> {
        compositeDisposable.add(
            interactor.getData(word, isOnline)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe {
                    _liveData.value = AppState.Loading(progress = null)
                    //TODO Реализовать прогресс загрузки
                }
                .subscribeWith(getObserver())
        )
        return liveData
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
}
