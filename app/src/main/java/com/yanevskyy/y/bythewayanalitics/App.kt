package com.yanevskyy.y.bythewayanalitics

import android.app.Application
import android.preference.PreferenceManager
import com.yanevskyy.y.bythewayanalitics.catching_users.DAY_TIME
import com.yanevskyy.y.bythewayanalitics.catching_users.LoadUsersScheduler
import com.yanevskyy.y.bythewayanalitics.di.byTheWayStatisticModules
import org.koin.android.ext.android.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, byTheWayStatisticModules)

        if (PreferenceManager.getDefaultSharedPreferences(this).getLong("END_START_LOADING_USERS", 0L) == 0L)
            LoadUsersScheduler.scheduleWithDelay(this, DAY_TIME)
    }
}