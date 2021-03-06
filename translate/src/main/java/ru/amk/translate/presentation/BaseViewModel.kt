package ru.amk.translate.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import ru.amk.core.entity.AppHistoryState
import ru.amk.core.entity.AppResponseState

abstract class BaseViewModel<T : AppResponseState>(
) : ViewModel() {

    protected var translateJob: Job? = null
    protected var historyJob: Job? = null
    abstract fun requestData(word: String, isOnline: Boolean)
    abstract fun getData()
    abstract fun handleError(error: Throwable)
    abstract fun saveWordHistory(word: String)

    protected val viewModelCoroutineScope = CoroutineScope(
        SupervisorJob()
            + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        })
    protected val _translateLiveData = MutableLiveData<AppResponseState>()

    val translateLiveData: LiveData<AppResponseState>
        get() {
            return _translateLiveData
        }
    protected val _historyLiveData = MutableLiveData<AppHistoryState>()

    val historyLiveData: LiveData<AppHistoryState>
        get() {
            return _historyLiveData
        }

    override fun onCleared() {
        super.onCleared()
        cancelScope()
    }

    protected fun cancelScope() {
        viewModelCoroutineScope.coroutineContext.cancel()
    }

    protected fun cancelJob() {
        translateJob?.cancel()
        historyJob?.cancel()
    }
}
