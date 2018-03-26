package com.yanevskyy.y.bythewayanalitics.statistic.IView

interface FragmentBudgetView {
    fun showCountsAndPercents(averageBudget: Long, maxBudget: Long, minBudget: Long)
}