package com.yanevskyy.y.bythewayanalitics.statistic.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yanevskyy.y.bythewayanalitics.R
import com.yanevskyy.y.bythewayanalitics.statistic.fragmentcontracts.SomethingFragmentSearchScreen
import com.yanevskyy.y.bythewayanalitics.statistic.presentercontracts.SomethingPresenterSearchScreen
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.android.ext.android.inject

class SearchScreenFragment : BaseFragment<SomethingFragmentSearchScreen>(), SomethingFragmentSearchScreen {
    override val presenter: SomethingPresenterSearchScreen by inject()
    override val view: SomethingFragmentSearchScreen = this

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_search, container, false)

    override fun startWork() {
        buttonSearch.setOnClickListener { textId.text = presenter.getUsersByName(textName.text.toString()) }
    }
}