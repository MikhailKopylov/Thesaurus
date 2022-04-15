package ru.amk.tesaurus.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.amk.tesaurus.model.network.api.RetrofitImpl
import ru.amk.tesaurus.model.network.data.DataModel
import ru.amk.tesaurus.model.repository.Repository
import ru.amk.tesaurus.model.repository.RepositoryImpl
import ru.amk.tesaurus.model.repository.RoomDataBaseImpl

internal const val REMOTE = "remote"
internal const val LOCAL = "local"

object AppModule {

    val appModule = module {
        single<Repository<List<DataModel>>>(named(REMOTE)) {
            RepositoryImpl(RetrofitImpl())
        }
        single<Repository<List<DataModel>>>(named(LOCAL)) {
            RepositoryImpl(RoomDataBaseImpl())
        }
    }
}
