package com.yanevskyy.y.bythewayanalitics.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yanevskyy.y.bythewayanalitics.App
import com.yanevskyy.y.bythewayanalitics.AppPresenter
import com.yanevskyy.y.bythewayanalitics.R
import kotlinx.android.synthetic.main.fragment_users_statistic.*
import java.util.*


class UsersStatistic : Fragment() {
    private var presenter: AppPresenter = App.INSTANCE.appPresenter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_users_statistic, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var countNotCreatedTrips = 0
        presenter.userDao.users.filter { user -> user.cities.isEmpty() }.forEach { countNotCreatedTrips++ }
        val percentsNotCreatedTrips = presenter.calculatePercents(countNotCreatedTrips, presenter.userDao.users.size)

        var countActiveTrips = 0
        val currentTime = Calendar.getInstance().timeInMillis - (1000 * 60 * 60 * 24)
        presenter.userDao.users
                .filter { user ->
                    user.dates["start_date"]?.let { it > currentTime }
                            ?: (user.dates["end_date"]?.let { it > currentTime } ?: false)
                }
                .forEach { countActiveTrips++ }
        val percentsCountActiveTrips = presenter.calculatePercents(countActiveTrips, presenter.userDao.users.size)

        displayValues(presenter.userDao.users.size, countNotCreatedTrips, percentsNotCreatedTrips, countActiveTrips, percentsCountActiveTrips)
    }

    private fun displayValues(countAllUsers: Int, countNotCreatedTrips: Int, percentsNotCreatedTrips: Int,
                              countActiveTrips: Int, percentsCountActiveTrips: Int) {
        countAllUsersText.text = StringBuilder(context?.getString(R.string.total_quantity_users)).append(" ")
                .append(countAllUsers.toString())
        countNotCreatedTripsText.text = StringBuilder(context?.getString(R.string.not_created_trips)).append(" ")
                .append(countNotCreatedTrips.toString()).append(" (").append(percentsNotCreatedTrips).append("%)")
        countActiveTripsText.text = StringBuilder(context?.getString(R.string.count_active_trips)).append(" ")
                .append(countActiveTrips.toString()).append(" (").append(percentsCountActiveTrips).append("%)")
    }
}