package com.yanevskyy.y.bythewayanalitics.fragment


import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import com.yanevskyy.y.bythewayanalitics.R
import com.yanevskyy.y.bythewayanalitics.UserDao
import java.util.*


class FragmentLastActivityUsers : Fragment() {
    lateinit var userDao: UserDao
    var timeLastActivityUser: Long = 0L
    lateinit var mDisplayDate: TextView
    lateinit var mDateListner: DatePickerDialog.OnDateSetListener
    lateinit var listView: ListView
    lateinit var calendar: Calendar
    lateinit var buttonSentMail: FloatingActionButton
    lateinit var adapter: ArrayAdapter<String>
    var items: MutableList<String> = ArrayList()
    var emails: MutableList<String> = ArrayList()


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val arg = arguments
        userDao = arg.getSerializable("USERS_DAO") as UserDao
        val view = inflater!!.inflate(R.layout.fragment_last_activity_users,
                container, false)

        listView = view.findViewById(R.id.userList)

        mDisplayDate = view.findViewById(R.id.textDateActive)
        mDisplayDate.setOnClickListener {
            calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val dialog = DatePickerDialog(context,
                    android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDateListner,
                    year, month, day)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()
        }

        mDateListner = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            mDisplayDate.text = StringBuilder(dayOfMonth.toString()).append("/").append(month + 1).append("/").append(year)

            val calendar1 = Calendar.getInstance()
            calendar1.set(year, month, dayOfMonth)
            timeLastActivityUser = calendar1.time.time
            items = getActiveUsers()
            adapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, items)
            listView.adapter = adapter
            listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
                items.remove(items[position])
                adapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, items)
                listView.adapter = adapter
            }
        }

        buttonSentMail = view.findViewById<View>(R.id.fab2) as FloatingActionButton
        buttonSentMail.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "message/rfc822"
            intent.putExtra(Intent.EXTRA_SUBJECT, emails.toTypedArray())
            intent.putExtra(Intent.EXTRA_TEXT, "Body of email")
            //   intent.setData(Uri.parse("mailto:default@recipient.com")); // or just "mailto:" for blank
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) // this will make such that when user returns to your app, your app is displayed, instead of the email app.
            startActivity(intent)
        }


        return view
    }
    //fixme onDestroyView

    fun onClickButtonEmail() {}

    fun getActiveUsers(): MutableList<String> {
        val userNames = ArrayList<String>()
        for (user in userDao.users) {
            val timestamp = user.data
            if (timestamp <= timeLastActivityUser) {
                val name = user.name
                val lastName = user.lastName
                val email = user.email
                emails.add(email)
                if (!email.isEmpty() && !email.equals("null", ignoreCase = true)) {
                    userNames.add("$name $lastName : $email")
                }

            }
        }
        return userNames

    }

}// Required empty public constructor
