package ru.amk.tesaurus.di

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import ru.amk.tesaurus.model.network.api.RetrofitImpl
import ru.amk.tesaurus.model.network.data.DataModel
import ru.amk.tesaurus.model.repository.DataSource
import ru.amk.tesaurus.model.repository.Repository
import ru.amk.tesaurus.model.repository.RepositoryImpl
import ru.amk.tesaurus.model.repository.RoomDataBaseImpl
import ru.amk.tesaurus.rx.SchedulerProvider
import ru.amk.tesaurus.rx.SchedulerProviderRx
import javax.inject.Named
import javax.inject.Singleton

internal const val REMOTE_REPOSITORY = "Remote"
internal const val LOCAL_REPOSITORY = "Locale"

@Module
class ModelModule {

    @Provides
    @Singleton
    @Named(REMOTE_REPOSITORY)
    internal fun provideRepositoryRemote(
        @Named(REMOTE_REPOSITORY) dataSourceRemote: DataSource<List<DataModel>>
    ): Repository<List<DataModel>> =
        RepositoryImpl(dataSourceRemote)

    @Provides
    @Singleton
    @Named(LOCAL_REPOSITORY)
    internal fun provideRepositoryLocal(
        @Named(LOCAL_REPOSITORY) dataSourceLocal: DataSource<List<DataModel>>
    ): Repository<List<DataModel>> =
        RepositoryImpl(dataSourceLocal)

    @Provides
    @Singleton
    @Named(REMOTE_REPOSITORY)
    internal fun provideDataSourceRemote(): DataSource<List<DataModel>> = RetrofitImpl()

    @Provides
    @Singleton
    @Named(LOCAL_REPOSITORY)
    internal fun provideDataSourceLocal(): DataSource<List<DataModel>> = RoomDataBaseImpl()

    @Provides
    @Singleton
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    @Singleton
    fun provideSchedulerProvider(): SchedulerProvider = SchedulerProviderRx()

}
