package com.yanevskyy.y.bythewayanalitics.presenter

import android.content.Context
import android.content.Intent
import com.firebase.mm.myapplication.User
import com.yanevskyy.y.bythewayanalitics.activity.MainActivity
import com.yanevskyy.y.bythewayanalitics.model.UserDao
import com.yanevskyy.y.bythewayanalitics.repository.OnRequestedUsers
import com.yanevskyy.y.bythewayanalitics.repository.UserRepositoryContract

class SplashPresenter(val appContext: Context, val userDao: UserDao, private val repository: UserRepositoryContract) {
    fun installAllUsers(contextFrom: Context){
        repository.requestAllUsers(object : OnRequestedUsers() {
            override fun onSuccessRequested(users: MutableList<User>) {
                userDao.users = users
                contextFrom.startActivity(Intent(appContext, MainActivity::class.java))
            }
        })
    }
}