package com.yanevskyy.y.bythewayanalitics.statistic.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter
import com.yanevskyy.y.bythewayanalitics.R
import com.yanevskyy.y.bythewayanalitics.statistic.IView.FragmentStatisticByParamsView
import com.yanevskyy.y.bythewayanalitics.statistic.presenter.StatisticByParamsPresenter
import kotlinx.android.synthetic.main.fragment_statistic_by_parameters.*
import org.koin.android.ext.android.inject

class StatisticByParamsFragment : BaseFragment<FragmentStatisticByParamsView>(), FragmentStatisticByParamsView {
    override val presenter: StatisticByParamsPresenter by inject()
    override val view: FragmentStatisticByParamsView = this


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_statistic_by_parameters, container, false)

    override fun startWork() {
        presenter.calculateSexesAndMethodsAndYears()
    }

    override fun showCountsAndPercents(countSexM: Int, countSexW: Int, countSexAny: Int, countsMethods: Map<String, Int>, countAges: Map<String, Int>) {
        displaySex(countSexM, countSexW, countSexAny)
        displayYears(countAges)
        displayMethods(countsMethods)
    }

    private fun displaySex(countSexM: Int, countSexW: Int, countSexAny: Int) {
        countAnyNetworkText.text = StringBuilder(context?.getString(R.string.count_mans)).append(" ").append(countSexM)
        countSexWText.text = StringBuilder(context?.getString(R.string.count_woman)).append(" ").append(countSexW)
        countSexAnyText.text = StringBuilder(context?.getString(R.string.count_any_sex)).append(" ").append(countSexAny)
    }

    private fun displayMethods(countsMethods: Map<String, Int>) {
        val methodsData: ArrayList<Map<String, String>> = ArrayList()
        countsMethods.map { currentAgePair ->
            methodsData.add(mutableMapOf(ATTRIBUTE_YEAR_GROUP to currentAgePair.key, ATTRIBUTE_YEAR_COUNT to currentAgePair.value.toString()))
        }
        listMethods.adapter = SimpleAdapter(context, methodsData, R.layout.count_groups_item, arrayOf(ATTRIBUTE_YEAR_GROUP, ATTRIBUTE_YEAR_COUNT),
                intArrayOf(R.id.type_group, R.id.count_group))
    }

    private fun displayYears(countAges: Map<String, Int>) {
        val yearsData: ArrayList<Map<String, String>> = ArrayList()
        countAges.entries.forEach { currentAgePair ->
            yearsData.add(mutableMapOf(ATTRIBUTE_YEAR_GROUP to currentAgePair.key, ATTRIBUTE_YEAR_COUNT to currentAgePair.value.toString()))
        }
        listYears.adapter = SimpleAdapter(context, yearsData, R.layout.count_groups_item, arrayOf(ATTRIBUTE_YEAR_GROUP, ATTRIBUTE_YEAR_COUNT),
                intArrayOf(R.id.type_group, R.id.count_group))
    }
}

const val ATTRIBUTE_YEAR_GROUP = "ATTRIBUTE_YEAR_GROUP"
const val ATTRIBUTE_YEAR_COUNT = "ATTRIBUTE_YEAR_COUNT"