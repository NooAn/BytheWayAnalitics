package com.yanevskyy.y.bythewayanalitics.statistic.IView

interface FragmentStatisticByParamsView {
    fun showCountsAndPercents(countSexM: Int, countSexW: Int, countSexAny: Int, countsMethods: Map<String, Int>, countAges: Map<String, Int>)
}