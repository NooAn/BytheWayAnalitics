package com.yanevskyy.y.bythewayanalitics.statistic.fragment

import android.support.v4.app.Fragment
import com.yanevskyy.y.bythewayanalitics.presenter.BasePresenter

abstract class BaseFragment<out ViewType> : Fragment() {
//    abstract val presenter: BasePresenter<ViewType>
    abstract val presenter: Any
    abstract val view: ViewType

    override fun onResume() {
        super.onResume()
        (presenter as BasePresenter<ViewType>).attachView(view)
        startWork()
    }

    override fun onPause() {
        super.onPause()
        (presenter as BasePresenter<*>).detachView()
//        presenter.detachView()
    }

    abstract fun startWork()
}