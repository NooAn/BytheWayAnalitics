package com.yanevskyy.y.bythewayanalitics.presenter

import com.yanevskyy.y.bythewayanalitics.model.UserDao

data class StatisticPresenter(val userDao: UserDao){
    fun calculatePercents(calculatingValue: Int, fullValue: Int) =
            Math.round(calculatingValue.toDouble() / fullValue * 100).toInt()
}