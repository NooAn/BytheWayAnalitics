package com.yanevskyy.y.bythewayanalitics.mvp.view

interface FragmentSearchScreenView {
    fun displayNamesById(users: String)
    fun date(start: String, middle: String, end: String)
    fun citys(start: String, middle: String, end: String)
}