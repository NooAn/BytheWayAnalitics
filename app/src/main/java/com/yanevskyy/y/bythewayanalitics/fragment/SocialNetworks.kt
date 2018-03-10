package com.yanevskyy.y.bythewayanalitics.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.firebase.mm.myapplication.SocialNetwork
import com.yanevskyy.y.bythewayanalitics.App
import com.yanevskyy.y.bythewayanalitics.AppPresenter
import com.yanevskyy.y.bythewayanalitics.R
import kotlinx.android.synthetic.main.fragment_social_networks.*

class SocialNetworks : Fragment() {
    private var presenter: AppPresenter = App.INSTANCE.appPresenter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_social_networks, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val networks = mutableMapOf<String, Int>()
        SocialNetwork.values().forEach { network -> networks[network.name] = 0 }
        var countAnyNetworks = 0
        presenter.userDao.users.filter { user -> user.socialNetwork.isNotEmpty() }
                .forEach { user ->
                    user.socialNetwork.forEach { currentUsersNetwork ->
                        networks[currentUsersNetwork.key]?.let { networks[currentUsersNetwork.key] = it.inc() }
                        countAnyNetworks++
                    }
                }
        val percentsNetworks = mutableMapOf<String, Int>()
        networks.forEach { currentNetwork ->
            percentsNetworks[currentNetwork.key] = Math.round(currentNetwork.value.toDouble() / countAnyNetworks * 100).toInt()
        }
        displayValues(countAnyNetworks, percentsNetworks, networks)
    }

    private fun displayValues(countAnyNetworks: Int, percentsNetworks: MutableMap<String, Int>, networks: MutableMap<String, Int>) {
        countAnyNetworkText.text = StringBuilder(context?.getString(R.string.contains_any_networks)).append(" ")
                .append(countAnyNetworks)
        networks.forEach { networkCount ->
            percentsNetworks[networkCount.key]?.let { percentsNetwork ->
                val displayCount = when (networkCount.key) {
                    in SocialNetwork.CS.name -> countCSText
                    in SocialNetwork.FB.name -> countFBText
                    in SocialNetwork.TG.name -> countTGText
                    in SocialNetwork.VK.name -> countVKText
                    in SocialNetwork.WHATSAPP.name -> countWhatsappText
                    else -> null
                }
                displayCount?.text = StringBuilder().append(" ").append(networkCount).append(" (")
                        .append(percentsNetwork).append("%").append(")")
            }
        }
    }
}