package ru.amk.tesaurus

import android.app.Application
import org.koin.core.context.startKoin
import ru.amk.tesaurus.di.AppModule.appModule
import ru.amk.tesaurus.di.MainActivityModule.activityModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(appModule, activityModule))
        }
    }
}
