package com.yanevskyy.y.bythewayanalitics.statistic.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yanevskyy.y.bythewayanalitics.R
import com.yanevskyy.y.bythewayanalitics.statistic.fragmentcontracts.SomethingFragmentAddInformation
import com.yanevskyy.y.bythewayanalitics.statistic.presentercontracts.SomethingPresenterAddInformation
import kotlinx.android.synthetic.main.fragment_add_info.*
import org.koin.android.ext.android.inject

class AddInformationFragment : BaseFragment<SomethingFragmentAddInformation>(), SomethingFragmentAddInformation {
    override val presenter: SomethingPresenterAddInformation by inject()
    override val view: SomethingFragmentAddInformation = this

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_add_info, container, false)

    override fun startWork() {
        showCountInfo(presenter.calculateCountInfo())
    }

    override fun showCountInfo(countInfo: Int) {
        countAddInformationText.text = StringBuilder(countAddInformationText.text).append(countInfo)
    }
}