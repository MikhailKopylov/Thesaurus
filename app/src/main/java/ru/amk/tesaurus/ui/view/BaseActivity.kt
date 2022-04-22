package ru.amk.tesaurus.ui.view

import androidx.appcompat.app.AppCompatActivity
import ru.amk.tesaurus.entity.AppResponseState
import ru.amk.tesaurus.presentation.BaseViewModel

abstract class BaseActivity<T : AppResponseState> : AppCompatActivity() {


    abstract val model: BaseViewModel<T>

    abstract fun renderData(appResponseState: AppResponseState)

    abstract fun renderHistoryData()
}
