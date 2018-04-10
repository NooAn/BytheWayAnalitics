package com.yanevskyy.y.bythewayanalitics.mvp.view

interface FragmentStatisticByParamsView {
    fun showCountsAndPercents(countSexM: Int, countSexW: Int, countSexAny: Int, countsMethods: Map<String, Int>, countAges: Map<String, Int>)
}