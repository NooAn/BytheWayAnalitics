package com.yanevskyy.y.bythewayanalitics.mvp.presenter

import com.yanevskyy.y.bythewayanalitics.mvp.model.UsersContainer

open class BasePresenter<ViewType>(val usersContainer: UsersContainer){
    protected var presentedView: ViewType? = null

    fun attachView(view: ViewType){
        presentedView = view
    }

    fun detachView() {
        presentedView = null
    }
}