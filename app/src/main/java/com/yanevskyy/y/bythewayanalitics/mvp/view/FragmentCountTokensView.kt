package com.yanevskyy.y.bythewayanalitics.mvp.view

interface FragmentCountTokensView {
    fun showCountInfo(countAllUsersWithTokens: Int, countActiveUsersWithTokens: Int)
    fun takeListTokens(tokes: ArrayList<String>)
}