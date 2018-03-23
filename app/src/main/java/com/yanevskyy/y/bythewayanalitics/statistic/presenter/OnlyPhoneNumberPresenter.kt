package com.yanevskyy.y.bythewayanalitics.statistic.presenter

import com.yanevskyy.y.bythewayanalitics.model.UsersContainer
import com.yanevskyy.y.bythewayanalitics.statistic.IView.SomethingFragmentPhoneNumber
import com.yanevskyy.y.bythewayanalitics.statistic.presentersLol.BaseSomethingPresenterStatistic
import com.yanevskyy.y.bythewayanalitics.statistic.presentersLol.SomethingPresenterOnlyPhoneNumber

class OnlyPhoneNumberPresenter(usersContainer: UsersContainer) : BaseSomethingPresenterStatistic<SomethingFragmentPhoneNumber>(usersContainer), SomethingPresenterOnlyPhoneNumber {
    override fun calculateCountUsersWithNumber() {
        presentedView?.displayCountUsersWithNumbers(usersContainer.users.count { user ->
            (user.email.isEmpty() || user.email.equals("null", true))
                    && user.phone.isNotEmpty() && !user.phone.equals("null", true)
        })
    }
}