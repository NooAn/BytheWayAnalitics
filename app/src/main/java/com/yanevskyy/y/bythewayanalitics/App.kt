package com.yanevskyy.y.bythewayanalitics

import android.app.Application
import com.yanevskyy.y.bythewayanalitics.dagger.*

class App : Application() {
    companion object {
        lateinit var INSTANCE: App
        lateinit var component: AppComponent
    }


    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        component = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    fun appComponent(): AppComponent = component
}