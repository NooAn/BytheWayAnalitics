package com.yanevskyy.y.bythewayanalitics.statistic.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yanevskyy.y.bythewayanalitics.R
import com.yanevskyy.y.bythewayanalitics.statistic.IView.FragmentTopCitiesView
import com.yanevskyy.y.bythewayanalitics.statistic.presenter.TopCitiesPresenter
import kotlinx.android.synthetic.main.fragment_top_cities.*
import org.koin.android.ext.android.inject

class TopCitiesFragment : BaseFragment<FragmentTopCitiesView>(), FragmentTopCitiesView {
    override val presenter: TopCitiesPresenter by inject()
    override val view: FragmentTopCitiesView = this


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_top_cities, container, false)

    override fun startWork() {
        presenter.calculateTopCities()
    }

    override fun displayTopCities(topFirstCities: String, topLastCities: String) {
        topFirstCityText.text = StringBuilder(topFirstCityText.text).append(topFirstCities)
        topLastCityText.text = StringBuilder(topLastCityText.text).append(topLastCities)
    }
}