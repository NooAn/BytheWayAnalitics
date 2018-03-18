package com.yanevskyy.y.bythewayanalitics.statistic.presenter

import com.yanevskyy.y.bythewayanalitics.model.UsersContainer
import com.yanevskyy.y.bythewayanalitics.statistic.fragmentcontracts.SomethingFragmentSearchScreen
import com.yanevskyy.y.bythewayanalitics.statistic.presentercontracts.BaseSomethingPresenterStatistic
import com.yanevskyy.y.bythewayanalitics.statistic.presentercontracts.SomethingPresenterSearchScreen

class SearchScreenPresenter(usersContainer: UsersContainer) : BaseSomethingPresenterStatistic<SomethingFragmentSearchScreen>(usersContainer), SomethingPresenterSearchScreen {
    override fun getUsersByName(name: String): String = usersContainer.users.filter {
        it.name.contains(name, ignoreCase = true) || it.lastName.contains(name, ignoreCase = true)
    }.map { it.id }.toString()
}