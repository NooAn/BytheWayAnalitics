package com.yanevskyy.y.bythewayanalitics.statistic.presenter

import android.content.Context
import android.content.Intent
import com.yanevskyy.y.bythewayanalitics.model.UsersContainer
import com.yanevskyy.y.bythewayanalitics.statistic.fragmentcontracts.SomethingFragmentLastActivityUsers
import com.yanevskyy.y.bythewayanalitics.statistic.presentercontracts.BaseSomethingPresenterStatistic
import com.yanevskyy.y.bythewayanalitics.statistic.presentercontracts.SomethingPresenterLastActivityUsers

class LastActivityUsersPresenter(usersContainer: UsersContainer) : BaseSomethingPresenterStatistic<SomethingFragmentLastActivityUsers>(usersContainer), SomethingPresenterLastActivityUsers {
    private var namesNotActiveUsers: MutableList<String> = ArrayList()
    private var emailsNotActiveUsers: MutableList<String> = ArrayList()

    override fun sendEmail(contextFrom: Context) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "message/rfc822"
        intent.putExtra(Intent.EXTRA_EMAIL, emailsNotActiveUsers.toTypedArray())
        intent.putExtra(Intent.EXTRA_TEXT, "Body of email")
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) // this will make such that when user returns to your app, your app is displayed, instead of the email app.
        contextFrom.startActivity(intent)
    }

    override fun installNotActiveUsers(minTimeLastActivityUser: Long) {
        emailsNotActiveUsers.clear()
        namesNotActiveUsers.clear()
        for (user in usersContainer.users) {
            val timestamp = user.data
            if (timestamp <= minTimeLastActivityUser) {
                val name = user.name
                val lastName = user.lastName
                val email = user.email
                if (email.isNotEmpty() && !email.equals("null", true)) {
                    emailsNotActiveUsers.add(user.email)
                    namesNotActiveUsers.add("$name $lastName : $email")
                }
            }
        }
    }

    override fun removeNotActiveUser(position: Int) {
        emailsNotActiveUsers.removeAt(position)
        namesNotActiveUsers.removeAt(position)
    }

    override fun namesNotActiveUsers(): List<String> = namesNotActiveUsers

    override fun emailsNotActiveUsers(): List<String> = emailsNotActiveUsers
}
