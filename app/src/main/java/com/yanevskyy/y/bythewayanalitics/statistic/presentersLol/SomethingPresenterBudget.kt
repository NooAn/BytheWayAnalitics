package com.yanevskyy.y.bythewayanalitics.statistic.presentersLol

import com.yanevskyy.y.bythewayanalitics.model.UsersContainer
import com.yanevskyy.y.bythewayanalitics.presenter.BasePresenter
import com.yanevskyy.y.bythewayanalitics.statistic.IView.SomethingFragmentBudget

abstract class SomethingPresenterBudget(usersContainer: UsersContainer): BasePresenter<SomethingFragmentBudget>(usersContainer){
    abstract fun calculateCountsAndPercentsBudgets()
}