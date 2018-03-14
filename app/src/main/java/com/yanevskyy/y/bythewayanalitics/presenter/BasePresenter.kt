package com.yanevskyy.y.bythewayanalitics.presenter

import com.yanevskyy.y.bythewayanalitics.model.UserDao

open class BasePresenter<T>(val userDao: UserDao) {
    protected var presentedView: T? = null

    fun attachView(view: T){
        presentedView = view
    }

    fun detachView() {
        presentedView = null
    }
}