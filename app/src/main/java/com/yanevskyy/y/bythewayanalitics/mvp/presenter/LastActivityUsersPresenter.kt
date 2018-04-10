package com.yanevskyy.y.bythewayanalitics.mvp.presenter

import com.yanevskyy.y.bythewayanalitics.mvp.model.UsersContainer
import com.yanevskyy.y.bythewayanalitics.mvp.view.FragmentLastActivityUsersView

class LastActivityUsersPresenter(usersContainer: UsersContainer) : BasePresenter<FragmentLastActivityUsersView>(usersContainer) {
    private var namesNotActiveUsers: MutableList<String> = mutableListOf()
    private var emailsNotActiveUsers: MutableList<String> = mutableListOf()


    fun sendEmailToNotActiveUsers() {
        presentedView?.senEmailToSelectedUsers(emailsNotActiveUsers)
    }

    fun installNotActiveUsers(minTimeLastActivityUser: Long) {
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
                    if (lastName.trim().isNotEmpty() || name.trim().isNotEmpty())
                        namesNotActiveUsers.add("$name $lastName : $email")
                    else
                        namesNotActiveUsers.add(email)
                }
            }
        }
        presentedView?.displayNamesNotActiveUsers(namesNotActiveUsers)
    }

    fun removeNotActiveUser(position: Int) {
        emailsNotActiveUsers.removeAt(position)
        namesNotActiveUsers.removeAt(position)
        presentedView?.displayNamesNotActiveUsers(namesNotActiveUsers)
    }
}
