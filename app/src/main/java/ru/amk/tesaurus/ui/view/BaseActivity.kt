package ru.amk.tesaurus.ui.view

import androidx.appcompat.app.AppCompatActivity
import ru.amk.tesaurus.entity.AppState
import ru.amk.tesaurus.presentation.BaseViewModel

abstract class BaseActivity<T : AppState> : AppCompatActivity() {


    abstract val model: BaseViewModel<T>

    abstract fun renderData(appState: AppState)

}
