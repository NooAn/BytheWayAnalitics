package com.yanevskyy.y.bythewayanalitics.statistic.presenter

import com.yanevskyy.y.bythewayanalitics.model.UserDao
import com.yanevskyy.y.bythewayanalitics.presenter.BasePresenter
import com.yanevskyy.y.bythewayanalitics.statistic.StatisticActivity

class StatisticActivityPresenter(userDao: UserDao): BasePresenter<StatisticActivity>(userDao){
    fun calculatePercents(calculatingValue: Int, fullValue: Int) =
            Math.round(calculatingValue.toDouble() / fullValue * 100).toInt()
}