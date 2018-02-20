package com.yanevskyy.y.bythewayanalitics.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.yanevskyy.y.bythewayanalitics.MainActivity

import com.yanevskyy.y.bythewayanalitics.R
import com.yanevskyy.y.bythewayanalitics.UserDao
import kotlinx.android.synthetic.main.fragment_budget.*

class BudgetFragment : Fragment() {
    lateinit var userDao: UserDao

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflater.inflate(R.layout.fragment_budget, container, false)!!

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        userDao = (activity as MainActivity).userDao

        var totalBudget = 0L
        var maxBudget = Long.MIN_VALUE
        var minBudget = Long.MAX_VALUE
        var countUsers = 0
        for (user in userDao.users) {
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
