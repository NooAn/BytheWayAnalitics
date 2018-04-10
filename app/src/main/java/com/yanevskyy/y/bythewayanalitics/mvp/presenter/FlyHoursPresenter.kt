package com.yanevskyy.y.bythewayanalitics.mvp.presenter

import com.yanevskyy.y.bythewayanalitics.mvp.model.UsersContainer
import com.yanevskyy.y.bythewayanalitics.mvp.view.FragmentFlyHoursView

class FlyHoursPresenter(usersContainer: UsersContainer) : BasePresenter<FragmentFlyHoursView>(usersContainer) {

    fun calculateCountHours() {
        presentedView?.showCountHours(usersContainer.users.count { user -> user.flightHours > 0 })
    }
}