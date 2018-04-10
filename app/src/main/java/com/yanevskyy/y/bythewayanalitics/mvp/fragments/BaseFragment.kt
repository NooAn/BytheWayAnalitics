package com.yanevskyy.y.bythewayanalitics.mvp.fragments

import android.support.v4.app.Fragment
import com.yanevskyy.y.bythewayanalitics.mvp.presenter.BasePresenter

abstract class BaseFragment<ViewType> : Fragment() {
    abstract val presenter: BasePresenter<ViewType>
    abstract val view: ViewType


    override fun onResume() {
        super.onResume()
        presenter.attachView(view)
        startWork()
    }

    override fun onPause() {
        super.onPause()
        presenter.detachView()
    }

    abstract fun startWork()
}