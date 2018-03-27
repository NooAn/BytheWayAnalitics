package com.yanevskyy.y.bythewayanalitics.statistic.fragment


import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.yanevskyy.y.bythewayanalitics.R
import com.yanevskyy.y.bythewayanalitics.statistic.IView.FragmentLastActivityUsersView
import com.yanevskyy.y.bythewayanalitics.statistic.presenter.LastActivityUsersPresenter
import kotlinx.android.synthetic.main.fragment_last_activity_users.*
import org.koin.android.ext.android.inject
import java.util.*


class LastActivityUsersFragment : BaseFragment<FragmentLastActivityUsersView>(), FragmentLastActivityUsersView {
    override val presenter: LastActivityUsersPresenter by inject()
    override val view: FragmentLastActivityUsersView = this
    private lateinit var calendar: Calendar


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_last_activity_users, container, false)

    override fun startWork() {
        updateActiveUsers(System.currentTimeMillis())

        textDateActive.setOnClickListener { showDateDialog() }

        startSendOnSelectedEmails.setOnClickListener { presenter.sendEmailToNotActiveUsers() }
    }

    override fun senEmailToSelectedUsers(users: List<String>) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "message/rfc822"
        intent.putExtra(Intent.EXTRA_EMAIL, users.toTypedArray())
        intent.putExtra(Intent.EXTRA_TEXT, "Body of email")
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    override fun displayNamesNotActiveUsers(names: List<String>) {
        text_count_users.text = StringBuilder("колличество пользователей: ").append(names.count())

        userList.adapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, names)

        userList.adapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, names)
        userList.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            removeNotActiveUser(position)
        }
    }


    private fun removeNotActiveUser(position: Int) {
        presenter.removeNotActiveUser(position)
    }

    override fun displayCountActiveUsers(count: Int) {
        text_count_users.text = StringBuilder("колличество пользователей: ").append(count).toString()
    }

    private fun updateActiveUsers(minTimeLastActivityUser: Long) {
        presenter.installNotActiveUsers(minTimeLastActivityUser)
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
                updateActiveUsers(calendarTimeLastActivityUser.timeInMillis)
            }
}