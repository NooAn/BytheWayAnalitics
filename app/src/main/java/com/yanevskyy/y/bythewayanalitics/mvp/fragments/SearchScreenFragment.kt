package com.yanevskyy.y.bythewayanalitics.mvp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.yanevskyy.y.bythewayanalitics.R
import com.yanevskyy.y.bythewayanalitics.mvp.view.FragmentSearchScreenView
import com.yanevskyy.y.bythewayanalitics.mvp.presenter.SearchScreenPresenter
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.android.ext.android.inject

class SearchScreenFragment : BaseFragment<FragmentSearchScreenView>(), FragmentSearchScreenView {

    override fun citys(start: String, middle: String, end: String) {
        textStartCity.set(start)
        textMiddleCity.set(middle)
        textEndCity.set(end)
    }

    override fun date(start: String, middle: String, end: String) {
        textStart.set(start)
        textMiddle.set(middle)
        textEnd.set(end)
    }

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
        textId.text = textId.text.toString().plus(users)
    }
}

private fun TextView.set(text: String) {
    this.text = this.text.toString().plus(" ").plus(text)
}
