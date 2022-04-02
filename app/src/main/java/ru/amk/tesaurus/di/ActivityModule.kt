package ru.amk.tesaurus.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.amk.tesaurus.MainActivity

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}
