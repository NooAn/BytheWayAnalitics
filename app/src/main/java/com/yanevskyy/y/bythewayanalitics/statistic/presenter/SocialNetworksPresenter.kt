package com.yanevskyy.y.bythewayanalitics.statistic.presenter

import com.firebase.mm.myapplication.SocialNetwork
import com.yanevskyy.y.bythewayanalitics.model.UsersContainer
import com.yanevskyy.y.bythewayanalitics.presenter.BasePresenter
import com.yanevskyy.y.bythewayanalitics.statistic.IView.FragmentSocialNetworksView
import com.yanevskyy.y.bythewayanalitics.statistic.calculatePercents

class SocialNetworksPresenter(usersContainer: UsersContainer) : BasePresenter<FragmentSocialNetworksView>(usersContainer) {
    private var countAnyNetworks = 0
    private val countsNetworks = mutableMapOf<String, Int>()
    private val percentsNetworks = mutableMapOf<String, Int>()


    fun calculateNetworks() {
        SocialNetwork.values().forEach { network -> countsNetworks[network.name] = 0 }
        calculateCountEachNetwork()
        calculatePercentsEachNetworks()
        presentedView?.showNetworks(countAnyNetworks, countsNetworks, percentsNetworks)
    }

    private fun calculateCountEachNetwork() {
        usersContainer.users.filter { user -> user.socialNetwork.isNotEmpty() }
                .forEach { user ->
                    countAnyNetworks++
                    user.socialNetwork.forEach { currentUsersNetworkPair ->
                        countsNetworks[currentUsersNetworkPair.key]?.let { countsNetworks[currentUsersNetworkPair.key] = it.inc() }
                    }
                }
    }

    private fun calculatePercentsEachNetworks() {
        countsNetworks.forEach { currentNetworkPair ->
            percentsNetworks[currentNetworkPair.key] = calculatePercents(currentNetworkPair.value, countAnyNetworks)
        }
    }
}