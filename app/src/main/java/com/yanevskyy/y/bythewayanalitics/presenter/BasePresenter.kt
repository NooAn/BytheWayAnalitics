package com.yanevskyy.y.bythewayanalitics.presenter

open class BasePresenter<T> {
    protected var presentedView: T? = null

    fun attachView(view: T){
        presentedView = view
    }

    fun detachView() {
        presentedView = null
    }
}