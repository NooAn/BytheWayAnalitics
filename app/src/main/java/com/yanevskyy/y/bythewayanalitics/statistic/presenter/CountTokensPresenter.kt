package com.yanevskyy.y.bythewayanalitics.statistic.presenter

import com.firebase.mm.myapplication.START_DATE_TRIP
import com.yanevskyy.y.bythewayanalitics.model.UsersContainer
import com.yanevskyy.y.bythewayanalitics.presenter.BasePresenter
import com.yanevskyy.y.bythewayanalitics.statistic.IView.FragmentCountTokensView

class CountTokensPresenter(usersContainer: UsersContainer) : BasePresenter<FragmentCountTokensView>(usersContainer) {
    fun calculateCountTokens() {
        val currentTime = System.currentTimeMillis()
        val listAllUsersWithTokens = usersContainer.users.filter { user -> user.token.isNotEmpty() }
        val countActiveUsersWithTokens = listAllUsersWithTokens.filter { user ->
            user.countTrip > 0 && (user.dates[START_DATE_TRIP]?.let { it > currentTime }
                    ?: false) && user.socialNetwork.isEmpty()
        }.count()
        presentedView?.showCountInfo(listAllUsersWithTokens.count(), countActiveUsersWithTokens)
    }
}