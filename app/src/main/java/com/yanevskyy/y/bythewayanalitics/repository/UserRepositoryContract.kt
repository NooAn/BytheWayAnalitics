package com.yanevskyy.y.bythewayanalitics.repository

import android.content.ContentValues
import android.util.Log
import com.firebase.mm.myapplication.User

open interface UserRepositoryContract {
    fun requestAllUsers(listener: OnRequestedUsers)
}

abstract class OnRequestedUsers {
    abstract fun onSuccessRequested(users: MutableList<User>)
    fun onFailureRequested(error: Exception) {
        Log.w(ContentValues.TAG, "Error getting documents.", error)
    }
}