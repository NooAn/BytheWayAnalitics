package com.yanevskyy.y.bythewayanalitics.mvp.presenter

import com.yanevskyy.y.bythewayanalitics.mvp.model.UsersContainer
import com.yanevskyy.y.bythewayanalitics.mvp.view.FragmentPhoneNumberView

class OnlyPhoneNumberPresenter(usersContainer: UsersContainer) : BasePresenter<FragmentPhoneNumberView>(usersContainer) {

    fun calculateCountUsersWithNumber() {
        presentedView?.displayCountUsersWithNumbers(usersContainer.users.count { user ->
            (user.email.isEmpty() || user.email.equals("null", true))
                    && user.phone.isNotEmpty() && !user.phone.equals("null", true)
        })
    }
}