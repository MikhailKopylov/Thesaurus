package ru.amk.tesaurus.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.amk.tesaurus.entity.AppState
import ru.amk.tesaurus.presentation.Presenter

abstract class BaseActivity<T : AppState> : AppCompatActivity(), View {

    protected lateinit var presenter: Presenter<T, View>

    protected abstract fun createPresenter(): Presenter<T, View>

    abstract override fun renderData(appState: AppState)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter()
    }
}
