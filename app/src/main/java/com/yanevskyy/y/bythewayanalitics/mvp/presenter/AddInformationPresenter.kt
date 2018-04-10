package com.yanevskyy.y.bythewayanalitics.mvp.presenter

import com.yanevskyy.y.bythewayanalitics.mvp.model.UsersContainer
import com.yanevskyy.y.bythewayanalitics.mvp.view.FragmentAddInformationView

class AddInformationPresenter(usersContainer: UsersContainer) : BasePresenter<FragmentAddInformationView>(usersContainer) {

    fun calculateCountInfo(): Int {
        var countAddInformation = 0
        usersContainer.users.filter { user -> user.addInformation.isNotEmpty() && !user.addInformation.equals("null", true) }
                .forEach { countAddInformation++ }
        return countAddInformation
    }
}