package com.yanevskyy.y.bythewayanalitics.statistic.presenter

import com.yanevskyy.y.bythewayanalitics.model.UsersContainer
import com.yanevskyy.y.bythewayanalitics.statistic.IView.SomethingFragmentFlyHours
import com.yanevskyy.y.bythewayanalitics.statistic.presentersLol.BaseSomethingPresenterStatistic
import com.yanevskyy.y.bythewayanalitics.statistic.presentersLol.SomethingPresenterFlyHours

class FlyHoursPresenter(usersContainer: UsersContainer) : BaseSomethingPresenterStatistic<SomethingFragmentFlyHours>(usersContainer), SomethingPresenterFlyHours {
    override fun calculateCountHours() {
        presentedView?.showCountHours(usersContainer.users.count { user -> user.flightHours > 0  })
    }
}