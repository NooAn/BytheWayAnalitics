package com.yanevskyy.y.bythewayanalitics.statistic.IView

interface FragmentLastActivityUsersView {

    fun senEmailToSelectedUsers(users: List<String>)

    fun displayNamesNotActiveUsers(names: List<String>)

//    fun displayCountActiveUsers(count: Int)
}