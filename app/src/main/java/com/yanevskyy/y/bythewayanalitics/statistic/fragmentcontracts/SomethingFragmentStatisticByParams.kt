package com.yanevskyy.y.bythewayanalitics.statistic.fragmentcontracts

interface SomethingFragmentStatisticByParams {
    fun showCountsAndPercents(countSexM: Int, countSexW: Int, countSexAny: Int, countsMethods: Map<String, Int>, countAges: Map<String, Int>)
}