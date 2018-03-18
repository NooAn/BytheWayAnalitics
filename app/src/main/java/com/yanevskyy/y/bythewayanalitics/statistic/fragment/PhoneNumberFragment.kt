package com.yanevskyy.y.bythewayanalitics.statistic.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yanevskyy.y.bythewayanalitics.R
import com.yanevskyy.y.bythewayanalitics.statistic.fragmentcontracts.SomethingFragmentPhoneNumber
import com.yanevskyy.y.bythewayanalitics.statistic.presenter.OnlyPhoneNumberPresenter
import kotlinx.android.synthetic.main.fragment_only_phone_number.*
import org.koin.android.ext.android.inject

class PhoneNumberFragment : BaseFragment<SomethingFragmentPhoneNumber>(), SomethingFragmentPhoneNumber {
    override val presenter: OnlyPhoneNumberPresenter by inject()
    override val view: SomethingFragmentPhoneNumber = this

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_only_phone_number, container, false)

    override fun startWork() {
        presenter.calculateCountUsersWithNumber()
    }

    override fun displayCountUsersWithNumbers(countOnlyPhoneNumber: Int) {
        countOnlyPhoneNumberText.text = StringBuilder(countOnlyPhoneNumberText.text).append(countOnlyPhoneNumber)
    }
}