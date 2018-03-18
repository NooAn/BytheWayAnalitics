package com.yanevskyy.y.bythewayanalitics.statistic.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yanevskyy.y.bythewayanalitics.R
import com.yanevskyy.y.bythewayanalitics.statistic.fragmentcontracts.SomethingFragmentUsersStatistic
import com.yanevskyy.y.bythewayanalitics.statistic.presentercontracts.UsersStatisticPresenterContract
import kotlinx.android.synthetic.main.fragment_users_statistic.*
import org.koin.android.ext.android.inject


class UsersStatisticFragment : BaseFragment<SomethingFragmentUsersStatistic>(), SomethingFragmentUsersStatistic {
    override val presenter: UsersStatisticPresenterContract by inject()
    override val view: SomethingFragmentUsersStatistic = this

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_users_statistic, container, false)

    override fun startWork() {
        presenter.calculateStatistic()
    }

    override fun displayValues(countAllUsers: Int, percentsNotCreatedTrips: Int, countNotCreatedTrips: Int,
                               percentsCountActiveTrips: Int, countUsersExistOneDay: Int, countActiveTrips: Int) {
        countAllUsersText.text = StringBuilder(context?.getString(R.string.total_quantity_users)).append(" ")
                .append(countAllUsers)
        countNewUsersText.text = StringBuilder(context?.getString(R.string.count_new_users)).append(" ")
                .append(countUsersExistOneDay)
        countNotCreatedTripsText.text = StringBuilder(context?.getString(R.string.not_created_trips)).append(" ")
                .append(countNotCreatedTrips).append(" (").append(percentsNotCreatedTrips).append("%)")
        countActiveTripsText.text = StringBuilder(context?.getString(R.string.count_active_trips)).append(" ")
                .append(countActiveTrips).append(" (").append(percentsCountActiveTrips).append("%)")
    }
}