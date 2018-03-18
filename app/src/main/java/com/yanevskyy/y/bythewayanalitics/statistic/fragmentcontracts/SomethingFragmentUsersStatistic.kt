package com.yanevskyy.y.bythewayanalitics.statistic.fragmentcontracts

interface SomethingFragmentUsersStatistic {
    fun displayValues(countAllUsers: Int, percentsNotCreatedTrips: Int, countNotCreatedTrips: Int, percentsCountActiveTrips: Int,
                      countUsersExistOneDay: Int, countActiveTrips: Int)
}