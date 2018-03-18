package com.yanevskyy.y.bythewayanalitics.statistic.fragment


import android.app.DatePickerDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.yanevskyy.y.bythewayanalitics.R
import com.yanevskyy.y.bythewayanalitics.statistic.fragmentcontracts.SomethingFragmentLastActivityUsers
import com.yanevskyy.y.bythewayanalitics.statistic.presentercontracts.SomethingPresenterLastActivityUsers
import kotlinx.android.synthetic.main.fragment_last_activity_users.*
import org.koin.android.ext.android.inject
import java.util.*


class LastActivityUsersFragment : BaseFragment<SomethingFragmentLastActivityUsers>(), SomethingFragmentLastActivityUsers {
    override val presenter: SomethingPresenterLastActivityUsers by inject()
    override val view: SomethingFragmentLastActivityUsers = this
    private lateinit var calendar: Calendar


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_last_activity_users, container, false)

    override fun startWork() {
        updateActiveUsers(System.currentTimeMillis())

        textDateActive.setOnClickListener { showDateDialog() }

        startSendOnSelectedEmails.setOnClickListener { context?.let { context -> presenter.sendEmail(context) } }
    }

    private fun showDateDialog() {
        calendar = Calendar.getInstance()
        val dialog = DatePickerDialog(context, android.R.style.Theme_Holo_Light_Dialog_MinWidth, prepareDateListener(),
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
    }

    private fun prepareDateListener(): DatePickerDialog.OnDateSetListener =
            DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                textDateActive.text = StringBuilder(dayOfMonth.toString()).append("/").append(month + 1).append("/").append(year)

                val calendarTimeLastActivityUser = Calendar.getInstance()
                calendarTimeLastActivityUser.set(year, month, dayOfMonth)
                updateActiveUsers(calendarTimeLastActivityUser.time.time)
                userList.adapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, presenter.namesNotActiveUsers())
                userList.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
                    updateNotActiveUsersAdapter(position)
                }
            }

    private fun updateNotActiveUsersAdapter(position: Int) {
        presenter.removeNotActiveUser(position)
        userList.adapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, presenter.namesNotActiveUsers())
    }

    private fun updateActiveUsers(minTimeLastActivityUser: Long) {
        presenter.installNotActiveUsers(minTimeLastActivityUser)
        text_count_users.text = StringBuilder("колличество пользователей: ").append((presenter.emailsNotActiveUsers().size).toString())
    }
}