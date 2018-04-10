package com.yanevskyy.y.bythewayanalitics.mvp.view

interface FragmentLastActivityUsersView {

    fun senEmailToSelectedUsers(users: List<String>)

    fun displayNamesNotActiveUsers(names: List<String>)

//    fun displayCountActiveUsers(count: Int)
}