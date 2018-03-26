package com.yanevskyy.y.bythewayanalitics.statistic.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.firebase.mm.myapplication.SocialNetwork
import com.yanevskyy.y.bythewayanalitics.R
import com.yanevskyy.y.bythewayanalitics.statistic.IView.FragmentSocialNetworksView
import com.yanevskyy.y.bythewayanalitics.statistic.presenter.SocialNetworksPresenter
import kotlinx.android.synthetic.main.fragment_social_networks.*
import org.koin.android.ext.android.inject

class SocialNetworksFragment : BaseFragment<FragmentSocialNetworksView>(), FragmentSocialNetworksView {
    override val presenter: SocialNetworksPresenter by inject()
    override val view: FragmentSocialNetworksView = this


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_social_networks, container, false)

    override fun startWork() {
        presenter.calculateNetworks()
    }

    override fun showNetworks(countAnyNetworks: Int, countsNetworks: Map<String, Int>, percentsNetworks: Map<String, Int>) {
        countAnyNetworkText.text = StringBuilder(context?.getString(R.string.contains_any_networks)).append(" ")
                .append(countAnyNetworks)
        displayCountEachNetwork(countsNetworks, percentsNetworks)
    }

    private fun displayCountEachNetwork(countsNetworks: Map<String, Int>, percentsNetworks: Map<String, Int>) {
        countsNetworks.forEach { networkCountPair ->
            percentsNetworks[networkCountPair.key]?.let { percentsNetworkPair ->
                delineateViewByNetwork(networkCountPair.key)?.text = StringBuilder(networkCountPair.key).append(": ")
                        .append(networkCountPair.value).append(" (").append(percentsNetworkPair).append("%").append(")")
            }
        }
    }

    private fun delineateViewByNetwork(networkName: String): TextView? {
        return when (networkName) {
            in SocialNetwork.CS.link -> countCSText
            in SocialNetwork.FB.link -> countFBText
            in SocialNetwork.TG.link -> countTGText
            in SocialNetwork.VK.link -> countVKText
            in SocialNetwork.WHATSAPP.link -> countWhatsAppText
            else -> null
        }
    }
}