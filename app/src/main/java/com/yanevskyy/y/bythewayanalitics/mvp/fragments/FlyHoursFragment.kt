package com.yanevskyy.y.bythewayanalitics.mvp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yanevskyy.y.bythewayanalitics.R
import com.yanevskyy.y.bythewayanalitics.mvp.view.FragmentFlyHoursView
import com.yanevskyy.y.bythewayanalitics.mvp.presenter.FlyHoursPresenter
import kotlinx.android.synthetic.main.fragment_flight_hours.*
import org.koin.android.ext.android.inject

class FlyHoursFragment : BaseFragment<FragmentFlyHoursView>(), FragmentFlyHoursView {
    override val presenter: FlyHoursPresenter by inject()
    override val view: FragmentFlyHoursView = this


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_flight_hours, container, false)

    override fun startWork() {
        presenter.calculateCountHours()
    }

    override fun showCountHours(countHours: Int) {
        countUsersWithFlightHoursPositiveText.text = StringBuilder(countUsersWithFlightHoursPositiveText.text).append(countHours)
    }
}