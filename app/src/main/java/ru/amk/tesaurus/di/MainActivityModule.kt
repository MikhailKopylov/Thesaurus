package ru.amk.tesaurus.di

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.amk.core.model.data_source.DatabaseHistory
import ru.amk.core.model.data_source.RoomHistory
import ru.amk.core.model.repository.DataSourceHistory
import ru.amk.core.model.repository.HistoryRepository
import ru.amk.translate.interactors.MainHistoryInteractor
import ru.amk.translate.interactors.MainTranslateInteractor
import ru.amk.translate.presentation.MainActivityViewModel

object MainActivityModule {

    val activityModule = module {

        factory { MainTranslateInteractor(remoteRepository = get(named(REMOTE)), localRepository = get(named(LOCAL))) }

        factory { MainActivityViewModel(get(), get()) }


        factory { MainHistoryInteractor(dataSourceHistory = get()) }

        single<DataSourceHistory<List<RoomHistory>>> {
            HistoryRepository(
                Room.databaseBuilder(androidContext(), DatabaseHistory::class.java, "history_database")
                    .build()
            )
        }

    }
}
