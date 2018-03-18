package com.yanevskyy.y.bythewayanalitics.statistic.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yanevskyy.y.bythewayanalitics.R
import com.yanevskyy.y.bythewayanalitics.statistic.fragmentcontracts.SomethingFragmentFlyHours
import com.yanevskyy.y.bythewayanalitics.statistic.presentercontracts.SomethingPresenterFlyHours
import kotlinx.android.synthetic.main.fragment_flight_hours.*
import org.koin.android.ext.android.inject

class FlyHoursFragment : BaseFragment<SomethingFragmentFlyHours>(), SomethingFragmentFlyHours {
    override val presenter: SomethingPresenterFlyHours by inject()
    override val view: SomethingFragmentFlyHours = this

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_flight_hours, container, false)

    override fun startWork() {
        presenter.calculateCountHours()
    }

    override fun showCountHours(countHours: Int) {
        countUsersWithFlightHoursPositiveText.text = StringBuilder(countUsersWithFlightHoursPositiveText.text).append(countHours)
    }
}