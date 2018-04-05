package com.yanevskyy.y.bythewayanalitics.statistic.presenter

import com.yanevskyy.y.bythewayanalitics.model.UsersContainer
import com.yanevskyy.y.bythewayanalitics.presenter.BasePresenter
import com.yanevskyy.y.bythewayanalitics.statistic.IView.FragmentCountTokensView

class CountTokensPresenter(usersContainer: UsersContainer) : BasePresenter<FragmentCountTokensView>(usersContainer) {
    fun calculateCountTokens() {
        presentedView?.showCountInfo(usersContainer.users.filter { user -> user.token.isNotEmpty() }.count())
    }
}