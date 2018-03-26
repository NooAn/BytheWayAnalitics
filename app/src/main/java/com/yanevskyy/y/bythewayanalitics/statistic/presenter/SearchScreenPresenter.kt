package com.yanevskyy.y.bythewayanalitics.statistic.presenter

import com.yanevskyy.y.bythewayanalitics.model.UsersContainer
import com.yanevskyy.y.bythewayanalitics.presenter.BasePresenter
import com.yanevskyy.y.bythewayanalitics.statistic.IView.FragmentSearchScreenView

class SearchScreenPresenter(usersContainer: UsersContainer) : BasePresenter<FragmentSearchScreenView>(usersContainer) {

    private fun getUsersByName(name: String): String = usersContainer.users.filter {
        it.name.contains(name, ignoreCase = true) || it.lastName.contains(name, ignoreCase = true)
    }.map { it.id }.toString()

    fun calculateIdByNames(query: String) {
        presentedView?.displayNamesById(getUsersByName(query))
    }
}