package com.yanevskyy.y.bythewayanalitics.dagger

import android.app.Application
import android.content.Context
import com.yanevskyy.y.bythewayanalitics.App
import com.yanevskyy.y.bythewayanalitics.AppPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val application: App) {

    @Provides
    @Singleton
    fun providerApplicationContext(): Context = application.applicationContext

    @Provides
    @Singleton
    fun provideApplication(): Application = application

    @Provides
    @Singleton
    fun provideAppPresenter(): AppPresenter = AppPresenter()

}