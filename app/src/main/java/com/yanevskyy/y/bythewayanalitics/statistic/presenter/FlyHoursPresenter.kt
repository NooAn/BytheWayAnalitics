package com.yanevskyy.y.bythewayanalitics.statistic.presenter

import com.yanevskyy.y.bythewayanalitics.model.UsersContainer
import com.yanevskyy.y.bythewayanalitics.presenter.BasePresenter
import com.yanevskyy.y.bythewayanalitics.statistic.IView.FragmentFlyHoursView

class FlyHoursPresenter(usersContainer: UsersContainer) : BasePresenter<FragmentFlyHoursView>(usersContainer) {

    fun calculateCountHours() {
        presentedView?.showCountHours(usersContainer.users.count { user -> user.flightHours > 0 })
    }
}