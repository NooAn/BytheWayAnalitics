package com.yanevskyy.y.bythewayanalitics.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.firebase.mm.myapplication.Method
import com.yanevskyy.y.bythewayanalitics.App
import com.yanevskyy.y.bythewayanalitics.presenter.StatisticPresenter
import com.yanevskyy.y.bythewayanalitics.R
import kotlinx.android.synthetic.main.fragment_statistic_by_parameters.*
import android.widget.SimpleAdapter
import org.koin.android.ext.android.inject


class StatisticByParams : Fragment() {
    val presenter: StatisticPresenter by inject()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_statistic_by_parameters, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var countSexM = 0
        var countSexW = 0
        var countSexAny = 0
        val countsMethods = mutableMapOf(Method.BUS.link to 0,
                Method.TRAIN.link to 0,
                Method.PLANE.link to 0,
                Method.CAR.link to 0,
                Method.HITCHHIKING.link to 0)
        val countAges = mutableMapOf(ONE_TO_NINE to 0, TEN_TO_NINETEEN to 0, TWENTY_TO_TWENTY_NINE to 0, THIRTY_TO_THIRTY_NINE to 0,
                FORTY_TO_FORTY_NINE to 0, FIFTY_TO_FIFTY_NINE to 0, SIXTY_TO_SIXTY_NINE to 0, SEVENTY_TO_EIGHTY to 0)

        presenter.userDao.users.forEach { user ->
            when (user.sex) {
                M_SEX -> countSexM++
                W_SEX -> countSexW++
                else -> countSexAny++
            }
            user.method.filter { methodPair -> methodPair.value }.forEach { methodPair ->
                countsMethods[methodPair.key]?.let { countCurrentMethod -> countsMethods[methodPair.key] = countCurrentMethod.inc() }
            }
            val agesGroupId: String = when (user.age) {
                in 1..9 -> ONE_TO_NINE
                in 10..19 -> TEN_TO_NINETEEN
                in 20..29 -> TWENTY_TO_TWENTY_NINE
                in 30..39 -> THIRTY_TO_THIRTY_NINE
                in 40..49 -> FORTY_TO_FORTY_NINE
                in 50..59 -> FIFTY_TO_FIFTY_NINE
                in 60..69 -> SIXTY_TO_SIXTY_NINE
                in 70..80 -> SEVENTY_TO_EIGHTY
                else -> ""
            }
            if (countAges.isNotEmpty()) countAges[agesGroupId]?.let { countAges[agesGroupId] = it.inc() }
        }
        displayValues(countSexM, countSexW, countSexAny, countsMethods, countAges)
    }

    private fun displayValues(countSexM: Int, countSexW: Int, countSexAny: Int, countsMethods: Map<String, Int>, countAges: Map<String, Int>) {
        countSexMText.text = StringBuilder(context?.getString(R.string.count_mans)).append(" ").append(countSexM)
        countSexWText.text = StringBuilder(context?.getString(R.string.count_woman)).append(" ").append(countSexW)
        countSexAnyText.text = StringBuilder(context?.getString(R.string.count_any_sex)).append(" ").append(countSexAny)

        val yearsData: ArrayList<Map<String, String>> = ArrayList()
        countAges.forEach{currentAgePair ->
            yearsData.add(mutableMapOf(ATTRIBUTE_YEAR_GROUP to currentAgePair.key, ATTRIBUTE_YEAR_COUNT to currentAgePair.value.toString()))
        }
        listYears.adapter = SimpleAdapter(context, yearsData, R.layout.count_groups_item, arrayOf(ATTRIBUTE_YEAR_GROUP, ATTRIBUTE_YEAR_COUNT),
                intArrayOf(R.id.type_group, R.id.count_group))

        val methodsData: ArrayList<Map<String, String>> = ArrayList()
        countsMethods.forEach{currentAgePair ->
            methodsData.add(mutableMapOf(ATTRIBUTE_YEAR_GROUP to currentAgePair.key, ATTRIBUTE_YEAR_COUNT to currentAgePair.value.toString()))
        }
        listMethods.adapter = SimpleAdapter(context, methodsData, R.layout.count_groups_item, arrayOf(ATTRIBUTE_YEAR_GROUP, ATTRIBUTE_YEAR_COUNT),
                intArrayOf(R.id.type_group, R.id.count_group))
    }
}


const val ATTRIBUTE_YEAR_GROUP = "ATTRIBUTE_YEAR_GROUP"
const val ATTRIBUTE_YEAR_COUNT = "ATTRIBUTE_YEAR_COUNT"
const val M_SEX = 1
const val W_SEX = 2
const val ONE_TO_NINE = "1..9"
const val TEN_TO_NINETEEN = "10..19"
const val TWENTY_TO_TWENTY_NINE = "20..29"
const val THIRTY_TO_THIRTY_NINE = "30..39"
const val FORTY_TO_FORTY_NINE = "40..49"
const val FIFTY_TO_FIFTY_NINE = "50..59"
const val SIXTY_TO_SIXTY_NINE = "60..69"
const val SEVENTY_TO_EIGHTY = "70..80"