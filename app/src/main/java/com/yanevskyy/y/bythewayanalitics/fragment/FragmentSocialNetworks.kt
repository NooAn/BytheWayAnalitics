package com.yanevskyy.y.bythewayanalitics.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yanevskyy.y.bythewayanalitics.App
import com.yanevskyy.y.bythewayanalitics.AppPresenter
import com.yanevskyy.y.bythewayanalitics.R
import kotlinx.android.synthetic.main.fragment_social_networks.*

class FragmentSocialNetworks : Fragment() {
    private var presenter: AppPresenter = App.INSTANCE.appPresenter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_social_networks, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var countUsersWithSN = 0
        var countAccountsWithTG = 0
        var countAccountsWithVK = 0
        var countAccountsWithWHATSAPP = 0
        val countAllUsers = presenter.userDao.users.size
        presenter.userDao.users.filter { user -> user.socialNetwork.isNotEmpty() }
                .forEach { user ->
                    Log.d("tag", user.name + "::: user.socialNetwork: " + user.socialNetwork)
                    countUsersWithSN++
                    user.socialNetwork["TG"]?.let { countAccountsWithTG++ }
                    user.socialNetwork["VK"]?.let { countAccountsWithVK++ }
                    user.socialNetwork["WHATSAPP"]?.let { countAccountsWithWHATSAPP++ }
                }

        val percentAccountsWithTG = Math.round(countAccountsWithTG.toDouble() / countAllUsers * 100).toInt()
        val percentAccountsWithVK = Math.round(countAccountsWithVK.toDouble() / countAllUsers * 100).toInt()
        val percentAccountsWithWhatsapp = Math.round(countAccountsWithWHATSAPP.toDouble() / countAllUsers * 100).toInt()

        displayValues(percentAccountsWithTG, percentAccountsWithVK, percentAccountsWithWhatsapp)
    }

    private fun displayValues(percentAccountsWithTG: Int, percentAccountsWithVK: Int, percentAccountsWithWhatsapp: Int) {
        percentCountAccountsWithTGText.text = StringBuilder(percentCountAccountsWithTGText.text).append(percentAccountsWithTG).append("%")
        percentAccountsWithVKText.text = StringBuilder(percentAccountsWithVKText.text).append(percentAccountsWithVK).append("%")
        percentAccountsWithWhatsappText.text = StringBuilder(percentAccountsWithWhatsappText.text).append(percentAccountsWithWhatsapp).append("%")
    }
}