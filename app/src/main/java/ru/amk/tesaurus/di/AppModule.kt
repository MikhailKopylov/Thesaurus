package ru.amk.tesaurus.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.amk.core.model.network.api.RetrofitImpl
import ru.amk.core.model.network.data.DataModel
import ru.amk.core.model.repository.Repository
import ru.amk.core.model.repository.RepositoryImpl
import ru.amk.core.model.repository.RoomDataBaseImpl

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
