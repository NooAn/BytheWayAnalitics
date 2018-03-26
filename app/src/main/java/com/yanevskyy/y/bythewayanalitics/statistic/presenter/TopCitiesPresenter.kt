package com.yanevskyy.y.bythewayanalitics.statistic.presenter

import com.yanevskyy.y.bythewayanalitics.model.UsersContainer
import com.yanevskyy.y.bythewayanalitics.presenter.BasePresenter
import com.yanevskyy.y.bythewayanalitics.statistic.IView.FragmentTopCitiesView

class TopCitiesPresenter(usersContainer: UsersContainer) : BasePresenter<FragmentTopCitiesView>(usersContainer) {

    fun calculateTopCities() {
        val allFirstCities = HashMap<String, Int>()
        val allLastCities = HashMap<String, Int>()
        calculateCountCities(allFirstCities, allLastCities)
        presentedView?.displayTopCities(calculateTopFirstCity(allFirstCities), calculateTopLastCity(allLastCities))
    }

    private fun calculateCountCities(allFirstCities: HashMap<String, Int>, allLastCities: HashMap<String, Int>) {
        usersContainer.users.filter { user -> user.cities.isNotEmpty() }.forEach { user ->
            user.cities["first_city"]?.let { userFirstCity ->
                allFirstCities.put(userFirstCity, allFirstCities[userFirstCity]?.inc() ?: 1)
            }
            user.cities["last_city"]?.let { userLastCity ->
                allLastCities.put(userLastCity, allLastCities[userLastCity]?.inc() ?: 1)
            }
        }
    }

    private fun calculateTopLastCity(allLastCities: HashMap<String, Int>): String =
            allLastCities.entries.maxWith(Comparator { oneEntry, twoEntry -> oneEntry.value.compareTo(twoEntry.value) })?.key
                    ?: ""

    private fun calculateTopFirstCity(allFirstCities: HashMap<String, Int>): String =
            allFirstCities.entries.maxWith(Comparator { oneEntry, twoEntry -> oneEntry.value.compareTo(twoEntry.value) })?.key
                    ?: ""
}