package com.yanevskyy.y.bythewayanalitics.statistic.presentercontracts

import android.content.Context

interface SomethingPresenterLastActivityUsers {
    fun installNotActiveUsers(minTimeLastActivityUser: Long)
    fun sendEmail(contextFrom: Context)
    fun emailsNotActiveUsers(): List<String>
    fun namesNotActiveUsers(): List<String>
    fun removeNotActiveUser(position: Int)
}