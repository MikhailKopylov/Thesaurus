package ru.amk.tesaurus.ui.view

import ru.amk.tesaurus.entity.AppState

interface View {

    fun renderData(appState: AppState)
}
