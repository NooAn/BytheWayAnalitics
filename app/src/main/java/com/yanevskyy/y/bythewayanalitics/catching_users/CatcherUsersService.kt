package com.yanevskyy.y.bythewayanalitics.catching_users

import android.app.IntentService
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.preference.PreferenceManager
import com.firebase.mm.myapplication.User
import com.yanevskyy.y.bythewayanalitics.App
import com.yanevskyy.y.bythewayanalitics.catching_users.LoadUsersScheduler.Companion.NOTIFICATION_ID
import com.yanevskyy.y.bythewayanalitics.repository.OnRequestedUsers
import com.yanevskyy.y.bythewayanalitics.repository.UserRepositoryContract
import org.koin.android.ext.android.inject


class CatcherUsersService : IntentService("CatcherUsersService") {
    val dbManager: DbManager by inject()
    val userRepository: UserRepositoryContract by inject()

    override fun onHandleIntent(intent: Intent?) {
        if (isOnline()) {
            userRepository.requestAllUsers(object : OnRequestedUsers() {
                override fun onSuccessRequested(users: MutableList<User>) {
                    dbManager.insertNowNotExistUsers(users)
                    PreferenceManager.getDefaultSharedPreferences(this@CatcherUsersService).edit().putLong("END_START_LOADING_USERS", System.currentTimeMillis()).apply()
                    (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).cancel(NOTIFICATION_ID)
                    LoadUsersScheduler.scheduleWithDelay(this@CatcherUsersService)
                }
            })
        }
    }

    private fun isOnline(): Boolean {
        val netInfo = (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }
}