package com.yanevskyy.y.bythewayanalitics.statistic.presentersLol

import com.yanevskyy.y.bythewayanalitics.model.UsersContainer
import com.yanevskyy.y.bythewayanalitics.statistic.IView.SomethingFragmentStatisticByParams

abstract class StatisticByParamsPresenterContract(usersContainer: UsersContainer) : BaseSomethingPresenterStatistic<SomethingFragmentStatisticByParams>(usersContainer) {
    abstract fun calculateSexesAndMethodsAndYears()
}