package com.yanevskyy.y.bythewayanalitics.statistic.presenter

import com.yanevskyy.y.bythewayanalitics.model.UsersContainer
import com.yanevskyy.y.bythewayanalitics.statistic.presentersLol.SomethingPresenterBudget

class BudgetPresenter(usersContainer: UsersContainer) : SomethingPresenterBudget(usersContainer) {

    override fun calculateCountsAndPercentsBudgets() {
        var totalBudget = 0L
        var maxBudget = Long.MIN_VALUE
        var minBudget = Long.MAX_VALUE
        var countUsers = 0L
        usersContainer.users.filter { user -> user.cities.isNotEmpty() && user.budget > 0 }.forEach { user ->
            countUsers++
            totalBudget += user.budget
            if (maxBudget < user.budget) maxBudget = user.budget
            if (minBudget > user.budget) minBudget = user.budget
        }
        presentedView?.showCountsAndPercents(totalBudget / countUsers, maxBudget, minBudget)
    }
}