package com.yanevskyy.y.bythewayanalitics

import android.app.Application
import android.preference.PreferenceManager
import android.util.Log
import com.yanevskyy.y.bythewayanalitics.catchingusers.DAY_TIME
import com.yanevskyy.y.bythewayanalitics.catchingusers.LoadUsersScheduler
import com.yanevskyy.y.bythewayanalitics.catchingusers.PLANER_LOAD_ID_ON_BOOT
import com.yanevskyy.y.bythewayanalitics.di.byTheWayStatisticModules
import org.koin.android.ext.android.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, byTheWayStatisticModules)

        if (PreferenceManager.getDefaultSharedPreferences(this)
                        .getLong("END_START_LOADING_USERS_" + PLANER_LOAD_ID_ON_BOOT, 0L) <= 0L)
            LoadUsersScheduler.scheduleWithDelay(this, DAY_TIME)
    }
}