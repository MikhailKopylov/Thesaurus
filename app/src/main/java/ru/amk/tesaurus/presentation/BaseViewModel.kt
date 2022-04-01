package ru.amk.tesaurus.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import ru.amk.tesaurus.entity.AppState

abstract class BaseViewModel<T : AppState>(
    private val compositeDisposable: CompositeDisposable = CompositeDisposable(),
) : ViewModel() {

    protected val _liveData = MutableLiveData<AppState>()
    val liveData: LiveData<AppState>
        get() {
            return _liveData
        }

    abstract fun getData(word: String, isOnline: Boolean)

    override fun onCleared() {
        compositeDisposable.clear()
    }
}
