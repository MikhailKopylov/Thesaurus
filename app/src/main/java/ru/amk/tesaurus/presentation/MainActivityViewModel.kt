package ru.amk.tesaurus.presentation

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import ru.amk.tesaurus.entity.AppState
import ru.amk.tesaurus.presentation.interactors.MainInteractor
import ru.amk.tesaurus.rx.SchedulerProvider

class MainActivityViewModel(
    private val interactor: MainInteractor,
    private val compositeDisposable: CompositeDisposable,
    private val schedulerProvider: SchedulerProvider,
) : BaseViewModel<AppState>() {


    override fun getData(word: String, isOnline: Boolean) {
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
