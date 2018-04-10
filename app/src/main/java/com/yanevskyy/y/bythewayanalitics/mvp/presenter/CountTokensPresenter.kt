package com.yanevskyy.y.bythewayanalitics.mvp.presenter

import com.firebase.mm.myapplication.START_DATE_TRIP
import com.firebase.mm.myapplication.User
import com.yanevskyy.y.bythewayanalitics.WebService
import com.yanevskyy.y.bythewayanalitics.di.FireBaseNotification
import com.yanevskyy.y.bythewayanalitics.mvp.model.UsersContainer
import com.yanevskyy.y.bythewayanalitics.mvp.view.FragmentCountTokensView
import io.reactivex.Completable
import kotlin.math.log

class CountTokensPresenter(usersContainer: UsersContainer, service: WebService) : BasePresenter<FragmentCountTokensView>(usersContainer) {
    var mapService = service
    var listUsers = listOf<User>()
    fun calculateCountTokens() {
        val currentTime = System.currentTimeMillis()
        val listAllUsersWithTokens = usersContainer.users.filter { user -> user.token.isNotEmpty() }
        listUsers = listAllUsersWithTokens.filter { user ->
            user.countTrip > 0 && (user.dates[START_DATE_TRIP]?.let { it > currentTime }
                    ?: false) && user.socialNetwork.isEmpty()
        }
        presentedView?.showCountInfo(listAllUsersWithTokens.count(), listUsers.count())
    }

    fun sendText(text: String) {
        val notify = FireBaseNotification().apply {
            this.title = "ByTheWay"
            this.cmd = "empty"
            this.body = text
            this.value = " "
        }
//        listUsers.map {
//            sendNotifications(it.token, )
//        }
        sendNotifications("", notify)
    }


    fun sendNotifications(ids: String, notification: FireBaseNotification) {
        println("users $ids")
        mapService.sendNotifications(hashMapOf(
                "ids" to ids,
                "title" to notification.title,
                "body" to notification.body,
                "cmd" to notification.cmd,
                "value" to notification.value.toString()
        ))
    }
}
