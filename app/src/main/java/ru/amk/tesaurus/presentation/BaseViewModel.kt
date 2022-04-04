package ru.amk.tesaurus.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.*
import ru.amk.tesaurus.entity.AppState

abstract class BaseViewModel<T : AppState>(
    private val compositeDisposable: CompositeDisposable = CompositeDisposable(),
) : ViewModel() {

    abstract fun getData(word: String, isOnline: Boolean)
    abstract fun handleError(error: Throwable)

    protected val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.Main
            + SupervisorJob()
            + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        })

    protected val _liveData = MutableLiveData<AppState>()
    val liveData: LiveData<AppState>
        get() {
            return _liveData
        }

    override fun onCleared() {
        super.onCleared()
        cancelJob()
    }

    protected fun cancelJob() {
        viewModelCoroutineScope.coroutineContext.cancelChildren()
    }
}
