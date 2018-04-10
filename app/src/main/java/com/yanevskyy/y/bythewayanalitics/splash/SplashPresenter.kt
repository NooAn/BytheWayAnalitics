package com.yanevskyy.y.bythewayanalitics.splash

import com.firebase.mm.myapplication.User
import com.yanevskyy.y.bythewayanalitics.catchingusers.DbManager
import com.yanevskyy.y.bythewayanalitics.catchingusers.OnInstallDates
import com.yanevskyy.y.bythewayanalitics.mvp.model.UsersContainer
import com.yanevskyy.y.bythewayanalitics.mvp.presenter.BasePresenter
import com.yanevskyy.y.bythewayanalitics.repository.OnRequestedUsers
import com.yanevskyy.y.bythewayanalitics.repository.UserRepositoryContract

class SplashPresenter(usersContainer: UsersContainer, val dbManager: DbManager, private val repository: UserRepositoryContract) : BasePresenter<SplashActivityContract>(usersContainer) {

    fun installAllUsers() {
        repository.requestAllUsers(object : OnRequestedUsers() {
            override fun onSuccessRequested(users: MutableList<User>) {
                usersContainer.users = users
                presentedView?.onInstallUsers()
//                dbManager.installDatesInUsers(usersContainer.users.toMutableList(), object : OnInstallDates {
//                    override fun onInstalled() { presentedView?.onInstallUsers() }
//                })
            }
        })
    }
}