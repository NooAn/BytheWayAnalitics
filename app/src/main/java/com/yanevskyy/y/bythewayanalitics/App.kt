package com.yanevskyy.y.bythewayanalitics

import android.app.Application

/**
 * Created by Олег on 23.02.2018.
 */
class App : Application() {
    companion object {
        lateinit var INSTANCE: App
    }
    var appPresenter: AppPresenter = AppPresenter()

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }
}