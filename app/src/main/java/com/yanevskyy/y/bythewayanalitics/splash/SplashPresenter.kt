package com.yanevskyy.y.bythewayanalitics.splash

import com.firebase.mm.myapplication.User
import com.yanevskyy.y.bythewayanalitics.model.UserDao
import com.yanevskyy.y.bythewayanalitics.presenter.BasePresenter
import com.yanevskyy.y.bythewayanalitics.repository.OnRequestedUsers
import com.yanevskyy.y.bythewayanalitics.repository.UserRepositoryContract

class SplashPresenter(val userDao: UserDao, private val repository: UserRepositoryContract) : BasePresenter<SplashActivityContract>() {

    fun installAllUsers() {
        repository.requestAllUsers(object : OnRequestedUsers() {
            override fun onSuccessRequested(users: MutableList<User>) {
                userDao.users = users
                presentedView?.onInstallUsers()
            }
        })
    }
}