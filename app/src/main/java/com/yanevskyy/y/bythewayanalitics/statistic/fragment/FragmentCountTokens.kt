package com.yanevskyy.y.bythewayanalitics.statistic.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yanevskyy.y.bythewayanalitics.R
import com.yanevskyy.y.bythewayanalitics.statistic.IView.FragmentCountTokensView
import com.yanevskyy.y.bythewayanalitics.statistic.presenter.CountTokensPresenter
import kotlinx.android.synthetic.main.fragment_tokens.*
import org.koin.android.ext.android.inject

class FragmentCountTokens : BaseFragment<FragmentCountTokensView>(), FragmentCountTokensView {
    override val presenter: CountTokensPresenter by inject()
    override val view: FragmentCountTokensView = this

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_tokens, container, false)

    override fun startWork() {
        presenter.calculateCountTokens()
    }

    override fun showCountInfo(countAllUsersWithTokens: Int, countActiveUsersWithTokens: Int) {
        countAllUsersWithTokensText.text = StringBuilder("тех, кто имеет токен: ").append(countAllUsersWithTokens)
        countActiveUsersWithTokensText.text = StringBuilder("тех, кто имеет токен с активными поездками, но без соц-сетей: ").append(countActiveUsersWithTokens)
    }
}