package com.yanevskyy.y.bythewayanalitics.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yanevskyy.y.bythewayanalitics.App
import com.yanevskyy.y.bythewayanalitics.presenter.StatisticPresenter
import com.yanevskyy.y.bythewayanalitics.R
import kotlinx.android.synthetic.main.fragment_budget.*
import org.koin.android.ext.android.inject

class Budget : Fragment() {
    val presenter: StatisticPresenter by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_budget, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var totalBudget = 0L
        var maxBudget = Long.MIN_VALUE
        var minBudget = Long.MAX_VALUE
        var countUsers = 0
        presenter.userDao.users.filter { user -> user.cities.isNotEmpty() && user.budget > 0 }.forEach { user ->
            countUsers++
            totalBudget += user.budget
            if (maxBudget < user.budget) maxBudget = user.budget
            if (minBudget > user.budget) minBudget = user.budget
        }

        displayValues(maxBudget, minBudget, totalBudget / countUsers)
    }

    private fun displayValues(maxBudget: Long, minBudget: Long, averageBudget: Long) {
        maxBudgetText.text = StringBuilder(maxBudgetText.text).append(maxBudget.toString())
        minBudgetText.text = StringBuilder(minBudgetText.text).append(minBudget.toString())
        averageBudgetText.text = StringBuilder(averageBudgetText.text).append(averageBudget.toString())
    }
}
