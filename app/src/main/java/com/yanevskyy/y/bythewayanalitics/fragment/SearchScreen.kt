package com.yanevskyy.y.bythewayanalitics.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.yanevskyy.y.bythewayanalitics.R
import com.yanevskyy.y.bythewayanalitics.activity.MainActivity
import kotlinx.android.synthetic.main.fragment_search.*

/**
 * Created by Andrei_Gusenkov on 3/1/2018.
 */
class SearchScreen : android.support.v4.app.Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_search,
                container, false)

        view?.findViewById<Button>(R.id.buttonSearch)?.setOnClickListener {
            val text = textName.text.toString()
            val stringNewText = (activity as MainActivity).getUsers().userDao.users.filter {
                it.name.contains(text, ignoreCase = true) || it.lastName.contains(text, ignoreCase = true)
            }.map { it.id }.toString()
            view.findViewById<TextView>(R.id.textId).text = stringNewText
        }
        return view
    }
}