package ru.amk.tesaurus.di

import androidx.room.Room
import io.reactivex.disposables.CompositeDisposable
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.amk.tesaurus.model.dataSource.DatabaseHistory
import ru.amk.tesaurus.model.dataSource.RoomHistory
import ru.amk.tesaurus.model.repository.DataSourceHistory
import ru.amk.tesaurus.model.repository.HistoryRepository
import ru.amk.tesaurus.presentation.MainActivityViewModel
import ru.amk.tesaurus.presentation.interactors.MainHistoryInteractor
import ru.amk.tesaurus.presentation.interactors.MainTranslateInteractor

object MainActivityModule {

    val activityModule = module {
        scope(named("MainActivity")) {
            factory {
                MainTranslateInteractor(
                    remoteRepository = get(named(REMOTE)),
                    localRepository = get(named(LOCAL))
                )
            }

            factory { MainActivityViewModel(get(), get()) }

            scoped { CompositeDisposable() }

            factory { MainHistoryInteractor(dataSourceHistory = get()) }

            scoped<DataSourceHistory<List<RoomHistory>>> {
                HistoryRepository(
                    Room.databaseBuilder(androidContext(), DatabaseHistory::class.java, "history_database")
                        .build()
                )
            }
        }
    }
}
