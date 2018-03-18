package com.yanevskyy.y.bythewayanalitics.statistic.presenter

import com.yanevskyy.y.bythewayanalitics.model.UsersContainer
import com.yanevskyy.y.bythewayanalitics.statistic.fragmentcontracts.SomethingFragmentFlyHours
import com.yanevskyy.y.bythewayanalitics.statistic.presentercontracts.BaseSomethingPresenterStatistic
import com.yanevskyy.y.bythewayanalitics.statistic.presentercontracts.SomethingPresenterFlyHours

class FlyHoursPresenter(usersContainer: UsersContainer) : BaseSomethingPresenterStatistic<SomethingFragmentFlyHours>(usersContainer), SomethingPresenterFlyHours {
    override fun calculateCountHours() {
        presentedView?.showCountHours(usersContainer.users.count { user -> user.flightHours > 0  })
    }
}