package com.yanevskyy.y.bythewayanalitics.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup

import com.yanevskyy.y.bythewayanalitics.R

class BudgetFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?) = inflater!!.inflate(R.layout.fragment_budget, container, false)

    companion object {
        fun newInstance(param1: String, param2: String): BudgetFragment {
            return BudgetFragment()
        }
    }

}
