package com.yanevskyy.y.bythewayanalitics.statistic.presenter

import com.yanevskyy.y.bythewayanalitics.model.UsersContainer
import com.yanevskyy.y.bythewayanalitics.statistic.IView.SomethingFragmentUsersStatistic
import com.yanevskyy.y.bythewayanalitics.statistic.presentersLol.BaseSomethingPresenterStatistic
import com.yanevskyy.y.bythewayanalitics.statistic.presentersLol.UsersStatisticPresenterContract
import java.util.*
import java.util.concurrent.TimeUnit

class UsersStatisticPresenter(usersContainer: UsersContainer) : BaseSomethingPresenterStatistic<SomethingFragmentUsersStatistic>(usersContainer),
        UsersStatisticPresenterContract {

    override fun calculateStatistic() {
        var countNotCreatedTrips = 0
        var countActiveTrips = 0
        usersContainer.users.filter { user -> user.cities.isEmpty() }.forEach { countNotCreatedTrips++ }
        val percentsNotCreatedTrips = calculatePercents(countNotCreatedTrips, usersContainer.users.size)

        val currentTime = Calendar.getInstance().timeInMillis - (1000 * 60 * 60 * 24)
        usersContainer.users
                .filter { user ->
                    user.dates["start_date"]?.let { it > currentTime }
                            ?: (user.dates["end_date"]?.let { it > currentTime } ?: false)
                }
                .forEach { countActiveTrips++ }
        val percentsCountActiveTrips = calculatePercents(countActiveTrips, usersContainer.users.size)

        val timeOneDayAgo = System.currentTimeMillis() - TimeUnit.DAYS.toMillis(1)
        val countUsersExistOneDay = usersContainer.users.count { it.catchingDate > timeOneDayAgo || it.catchingDate == 0L }
        presentedView?.displayValues(usersContainer.users.size, percentsNotCreatedTrips, countNotCreatedTrips, percentsCountActiveTrips,
                countUsersExistOneDay, countActiveTrips)
    }
}