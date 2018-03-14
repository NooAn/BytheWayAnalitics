package com.yanevskyy.y.bythewayanalitics.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yanevskyy.y.bythewayanalitics.App
import com.yanevskyy.y.bythewayanalitics.presenter.StatisticPresenter
import com.yanevskyy.y.bythewayanalitics.R
import kotlinx.android.synthetic.main.fragment_flight_hours.*
import org.koin.android.ext.android.inject

class FlyHours : Fragment() {
    val presenter: StatisticPresenter by inject()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_flight_hours, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var countUsersWithFlightHoursPositive = 0
        presenter.userDao.users.filter { user -> user.flightHours > 0 }.forEach { countUsersWithFlightHoursPositive++ }

        displayValues(countUsersWithFlightHoursPositive)
    }

    private fun displayValues(countAddInformation: Int) {
        countUsersWithFlightHoursPositiveText.text = StringBuilder(countUsersWithFlightHoursPositiveText.text).append(countAddInformation.toString())
    }
}