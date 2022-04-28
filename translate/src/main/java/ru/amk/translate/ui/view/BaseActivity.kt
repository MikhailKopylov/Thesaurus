package ru.amk.translate.ui.view

import androidx.appcompat.app.AppCompatActivity
import ru.amk.core.entity.AppResponseState
import ru.amk.translate.presentation.BaseViewModel

abstract class BaseActivity<T : AppResponseState> : AppCompatActivity() {


    abstract val model: BaseViewModel<T>

    abstract fun renderData(appResponseState: AppResponseState)

    abstract fun renderHistoryData()
}
