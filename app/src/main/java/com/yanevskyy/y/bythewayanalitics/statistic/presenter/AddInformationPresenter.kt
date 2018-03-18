package com.yanevskyy.y.bythewayanalitics.statistic.presenter

import com.yanevskyy.y.bythewayanalitics.model.UsersContainer
import com.yanevskyy.y.bythewayanalitics.statistic.fragmentcontracts.SomethingFragmentAddInformation
import com.yanevskyy.y.bythewayanalitics.statistic.presentercontracts.SomethingPresenterAddInformation
import com.yanevskyy.y.bythewayanalitics.statistic.presentercontracts.BaseSomethingPresenterStatistic

//class AddInformationPresenter(usersContainer: UsersContainer) : SomethingPresenterAddInformation(usersContainer) {
class AddInformationPresenter(usersContainer: UsersContainer) : BaseSomethingPresenterStatistic<SomethingFragmentAddInformation>(usersContainer), SomethingPresenterAddInformation {
    override fun calculateCountInfo(): Int {
        var countAddInformation = 0
        usersContainer.users.filter { user -> user.addInformation.isNotEmpty() && !user.addInformation.equals("null", true) }
                .forEach { countAddInformation++ }
        return countAddInformation
    }
}