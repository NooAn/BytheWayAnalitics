package com.yanevskyy.y.bythewayanalitics.statistic.presentercontracts

import com.yanevskyy.y.bythewayanalitics.model.UsersContainer
import com.yanevskyy.y.bythewayanalitics.statistic.fragmentcontracts.SomethingFragmentStatisticByParams

abstract class StatisticByParamsPresenterContract(usersContainer: UsersContainer) : BaseSomethingPresenterStatistic<SomethingFragmentStatisticByParams>(usersContainer) {
    abstract fun calculateSexesAndMethodsAndYears()
}