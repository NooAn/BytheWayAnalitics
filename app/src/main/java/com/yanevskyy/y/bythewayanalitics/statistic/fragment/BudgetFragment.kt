package com.yanevskyy.y.bythewayanalitics.statistic.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yanevskyy.y.bythewayanalitics.R
import com.yanevskyy.y.bythewayanalitics.statistic.fragmentcontracts.SomethingFragmentBudget
import com.yanevskyy.y.bythewayanalitics.statistic.presentercontracts.SomethingPresenterBudget
import kotlinx.android.synthetic.main.fragment_budget.*
import org.koin.android.ext.android.inject

class BudgetFragment : BaseFragment<SomethingFragmentBudget>(), SomethingFragmentBudget {
    override val presenter: SomethingPresenterBudget by inject()
    override val view: SomethingFragmentBudget = this

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_budget, container, false)

    override fun startWork() {
        presenter.calculateCountsAndPercentsBudgets()
    }

    override fun showCountsAndPercents(averageBudget: Long, maxBudget: Long, minBudget: Long) {
        maxBudgetText.text = StringBuilder(maxBudgetText.text).append(maxBudget)
        minBudgetText.text = StringBuilder(minBudgetText.text).append(minBudget)
        averageBudgetText.text = StringBuilder(averageBudgetText.text).append(averageBudget)
    }
}