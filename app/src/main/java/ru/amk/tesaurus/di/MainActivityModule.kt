package ru.amk.tesaurus.di

import io.reactivex.disposables.CompositeDisposable
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.amk.tesaurus.presentation.MainActivityViewModel
import ru.amk.tesaurus.presentation.interactors.MainInteractor
import ru.amk.tesaurus.rx.SchedulerProvider
import ru.amk.tesaurus.rx.SchedulerProviderRx

object MainActivityModule {

    val activityModule = module {

        factory { MainInteractor(get(named(REMOTE)), get(named(LOCAL))) }

        factory { MainActivityViewModel(get(), get(), get()) }

        single { CompositeDisposable() }

        single<SchedulerProvider> { SchedulerProviderRx() }
    }
}
