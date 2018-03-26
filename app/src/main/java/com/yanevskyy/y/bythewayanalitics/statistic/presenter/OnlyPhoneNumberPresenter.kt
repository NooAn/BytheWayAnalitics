package com.yanevskyy.y.bythewayanalitics.statistic.presenter

import com.yanevskyy.y.bythewayanalitics.model.UsersContainer
import com.yanevskyy.y.bythewayanalitics.presenter.BasePresenter
import com.yanevskyy.y.bythewayanalitics.statistic.IView.FragmentPhoneNumberView

class OnlyPhoneNumberPresenter(usersContainer: UsersContainer) : BasePresenter<FragmentPhoneNumberView>(usersContainer) {

    fun calculateCountUsersWithNumber() {
        presentedView?.displayCountUsersWithNumbers(usersContainer.users.count { user ->
            (user.email.isEmpty() || user.email.equals("null", true))
                    && user.phone.isNotEmpty() && !user.phone.equals("null", true)
        })
    }
}