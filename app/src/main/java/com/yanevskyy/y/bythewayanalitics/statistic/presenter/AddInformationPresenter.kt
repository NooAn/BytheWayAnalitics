package com.yanevskyy.y.bythewayanalitics.statistic.presenter

import com.yanevskyy.y.bythewayanalitics.model.UsersContainer
import com.yanevskyy.y.bythewayanalitics.presenter.BasePresenter
import com.yanevskyy.y.bythewayanalitics.statistic.IView.FragmentAddInformationView

class AddInformationPresenter(usersContainer: UsersContainer) : BasePresenter<FragmentAddInformationView>(usersContainer) {

    fun calculateCountInfo(): Int {
        var countAddInformation = 0
        usersContainer.users.filter { user -> user.addInformation.isNotEmpty() && !user.addInformation.equals("null", true) }
                .forEach { countAddInformation++ }
        return countAddInformation
    }
}