package ru.amk.tesaurus.presentation

import ru.amk.tesaurus.entity.AppState
import ru.amk.tesaurus.ui.view.View

interface Presenter<T:AppState, V: View> {

    fun attachView(view: V)

    fun dettachView(view:V)

    fun getData(word: String, isOnline: Boolean)
}
