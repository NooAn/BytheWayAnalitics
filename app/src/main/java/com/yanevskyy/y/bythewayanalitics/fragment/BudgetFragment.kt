package com.yanevskyy.y.bythewayanalitics.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yanevskyy.y.bythewayanalitics.App
import com.yanevskyy.y.bythewayanalitics.AppPresenter
import com.yanevskyy.y.bythewayanalitics.R
import kotlinx.android.synthetic.main.fragment_budget.*

class BudgetFragment : Fragment() {
    private var presenter: AppPresenter = App.INSTANCE.appPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_budget, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var totalBudget = 0L
        var maxBudget = Long.MIN_VALUE
        var minBudget = Long.MAX_VALUE
        var countUsers = 0
        for (user in presenter.userDao.users) {
            if (user.cities.isEmpty()) continue
            countUsers++
            totalBudget += user.budget
            if (maxBudget < user.budget) maxBudget = user.budget
            if (minBudget > user.budget) minBudget = user.budget
        }

        val averageBudget = totalBudget / countUsers

        displayValues(maxBudget, minBudget, averageBudget)
    }

    private fun displayValues(maxBudget: Long, minBudget: Long, averageBudget: Long) {
        maxBudgetText.text = StringBuilder(maxBudgetText.text).append(maxBudget.toString())
        minBudgetText.text = StringBuilder(minBudgetText.text).append(minBudget.toString())
        averageBudgetText.text = StringBuilder(averageBudgetText.text).append(averageBudget.toString())
    }
}
