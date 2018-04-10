package com.yanevskyy.y.bythewayanalitics.mvp.presenter

import android.util.Log
import com.firebase.mm.myapplication.*
import com.yanevskyy.y.bythewayanalitics.mvp.model.UsersContainer
import com.yanevskyy.y.bythewayanalitics.mvp.view.FragmentSearchScreenView
import java.text.SimpleDateFormat
import java.util.*

class SearchScreenPresenter(usersContainer: UsersContainer) : BasePresenter<FragmentSearchScreenView>(usersContainer) {

    private fun getUsersByName(name: String): User = usersContainer.users.filter {
        it.name.contains(name, ignoreCase = true) || it.lastName.contains(name, ignoreCase = true)
    }.first()

    fun calculateIdByNames(query: String) {
        val user = getUsersByName(query)
        Log.e("LOG", "token " + user.token)
        presentedView?.displayNamesById(user.id)
        presentedView?.date(user.dates[START_DATE].toDate(), user.dates[TWO_DATE].toDate(), user.dates[END_DATE].toDate())
        presentedView?.citys(user.cities[FIRST_INDEX_CITY] ?: "", user.cities[TWO_INDEX_CITY]
                ?: "", user.cities[LAST_INDEX_CITY] ?: "")
    }
}

private fun Long?.toDate(): String {
    val format = SimpleDateFormat("dd.MM.yyyy", Locale.US)
    return format.format(Date(this ?: 0))
}
