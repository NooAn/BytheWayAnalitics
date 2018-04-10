package com.yanevskyy.y.bythewayanalitics.mvp.presenter

import com.firebase.mm.myapplication.Method
import com.firebase.mm.myapplication.User
import com.yanevskyy.y.bythewayanalitics.mvp.model.UsersContainer
import com.yanevskyy.y.bythewayanalitics.mvp.view.FragmentStatisticByParamsView

class StatisticByParamsPresenter(usersContainer: UsersContainer) : BasePresenter<FragmentStatisticByParamsView>(usersContainer) {
    private var countSexM = 0
    private var countSexW = 0
    private var countSexAny = 0
    private val countsMethods = mutableMapOf(Method.BUS.link to 0, Method.TRAIN.link to 0, Method.PLANE.link to 0,
            Method.CAR.link to 0, Method.HITCHHIKING.link to 0)
    private val countAges = mutableMapOf(ONE_TO_NINE to 0, TEN_TO_NINETEEN to 0, TWENTY_TO_TWENTY_NINE to 0, THIRTY_TO_THIRTY_NINE to 0,
            FORTY_TO_FORTY_NINE to 0, FIFTY_TO_FIFTY_NINE to 0, SIXTY_TO_SIXTY_NINE to 0, SEVENTY_TO_EIGHTY to 0)


    fun calculateSexesAndMethodsAndYears() {
        usersContainer.users.forEach { user ->
            delineateSex(user)
            delineateMethods(user)
            delineateAgesGroup(user)
        }
        presentedView?.showCountsAndPercents(countSexM, countSexW, countSexAny, countsMethods, countAges)
    }

    private fun delineateAgesGroup(user: User) {
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

    private fun delineateMethods(user: User) {
        user.method.filter { methodPair -> methodPair.value }.forEach { methodPair ->
            countsMethods[methodPair.key]?.let { countCurrentMethod -> countsMethods[methodPair.key] = countCurrentMethod.inc() }
        }
    }

    private fun delineateSex(user: User) {
        when (user.sex) {
            M_SEX -> countSexM++
            W_SEX -> countSexW++
            else -> countSexAny++
        }
    }
}

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