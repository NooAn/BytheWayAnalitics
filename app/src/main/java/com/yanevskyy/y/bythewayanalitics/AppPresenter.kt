package com.yanevskyy.y.bythewayanalitics

data class AppPresenter(var userDao: UserDao = UserDao()){
    fun calculatePercents(calculatingValue: Int, fullValue: Int) =
            Math.round(calculatingValue.toDouble() / fullValue * 100).toInt()
}