package com.yanevskyy.y.bythewayanalitics.mvp.view

interface FragmentUsersStatisticView {
    fun displayValues(countAllUsers: Int, percentsNotCreatedTrips: Int, countNotCreatedTrips: Int, percentsCountActiveTrips: Int,
                      countUsersExistOneDay: Int, countActiveTrips: Int)
}