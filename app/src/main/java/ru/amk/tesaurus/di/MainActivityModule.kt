package ru.amk.tesaurus.di

import io.reactivex.disposables.CompositeDisposable
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.amk.tesaurus.presentation.MainActivityViewModel
import ru.amk.tesaurus.presentation.interactors.MainInteractor

object MainActivityModule {

    val activityModule = module {

        factory { MainInteractor(remoteRepository = get(named(REMOTE)), localRepository = get(named(LOCAL))) }

        factory { MainActivityViewModel(get()) }

        single { CompositeDisposable() }

    }
}
