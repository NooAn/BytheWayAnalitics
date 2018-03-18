package com.yanevskyy.y.bythewayanalitics.statistic.presentercontracts

import com.yanevskyy.y.bythewayanalitics.model.UsersContainer
import com.yanevskyy.y.bythewayanalitics.presenter.BasePresenter

abstract class BaseSomethingPresenterStatistic<ViewType>(usersContainer: UsersContainer) : BasePresenter<ViewType>(usersContainer) {
    fun calculatePercents(calculatingValue: Int, fullValue: Int) =
            Math.round(calculatingValue.toDouble() / fullValue * 100).toInt()
}