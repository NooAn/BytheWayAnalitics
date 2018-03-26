package com.yanevskyy.y.bythewayanalitics.statistic.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yanevskyy.y.bythewayanalitics.R
import com.yanevskyy.y.bythewayanalitics.statistic.IView.FragmentSearchScreenView
import com.yanevskyy.y.bythewayanalitics.statistic.presenter.SearchScreenPresenter
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.android.ext.android.inject

class SearchScreenFragment : BaseFragment<FragmentSearchScreenView>(), FragmentSearchScreenView {
    override val presenter: SearchScreenPresenter by inject()
    override val view: FragmentSearchScreenView = this


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_search, container, false)

    override fun startWork() {
        buttonSearch.setOnClickListener {
            presenter.calculateIdByNames(textName.text.toString())
        }
    }

    override fun displayNamesById(users: String) {
        textId.text = users
    }
}