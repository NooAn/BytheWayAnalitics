package com.yanevskyy.y.bythewayanalitics.statistic.fragment

import android.support.v4.app.Fragment
import com.yanevskyy.y.bythewayanalitics.presenter.BasePresenter

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