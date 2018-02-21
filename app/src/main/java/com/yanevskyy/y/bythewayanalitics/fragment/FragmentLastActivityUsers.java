package com.yanevskyy.y.bythewayanalitics.fragment;


import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.mm.myapplication.User;
import com.yanevskyy.y.bythewayanalitics.R;
import com.yanevskyy.y.bythewayanalitics.UserDao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class FragmentLastActivityUsers extends Fragment {
    UserDao userDao;
    long timeLastActivityUser;
    TextView mDisplayDate;
    DatePickerDialog.OnDateSetListener mDateListner;
    ListView listView;
    Calendar calendar;
    FloatingActionButton buttonSentMail;
    ArrayAdapter<String> adapter;
    List<String> listActiveUsers = new ArrayList<>();
    List<String> emails = new ArrayList<>();

    public FragmentLastActivityUsers() {
        // Required empty public constructor
    }

    Context context;
    TextView textCount;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle arg = getArguments();
        userDao = (UserDao) arg.getSerializable("USERS_DAO");
        final View view = inflater.inflate(R.layout.fragment_last_activity_users,
                container, false);
        context = getContext();

        listView = view.findViewById(R.id.userList);
        textCount = view.findViewById(R.id.value_count_users);
        mDisplayDate = view.findViewById(R.id.textDateActive);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(context,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDateListner,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateListner = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                mDisplayDate.setText(date);

                Calendar calendar1 = Calendar.getInstance();
                calendar1.set(year, month - 1, dayOfMonth);
                timeLastActivityUser = calendar1.getTime().getTime();
                listActiveUsers = getActiveUsers();

                textCount.setText((String.valueOf(listActiveUsers.size())));

                adapter = new ArrayAdapter<String>(context,
                        android.R.layout.simple_list_item_1, listActiveUsers);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String item = listActiveUsers.get(position);
                        listActiveUsers.remove(item);
                        adapter = new ArrayAdapter<String>(context,
                                android.R.layout.simple_list_item_1, listActiveUsers);
                        listView.setAdapter(adapter);
                    }
                });

            }
        };

        buttonSentMail = (FloatingActionButton) view.findViewById(R.id.fab2);
        buttonSentMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("message/rfc822");
                intent.putExtra(Intent.EXTRA_SUBJECT, emails.toArray());
                intent.putExtra(Intent.EXTRA_TEXT, "Body of email");
                //   intent.setData(Uri.parse("mailto:default@recipient.com")); // or just "mailto:" for blank
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
                startActivity(intent);
            }
        });


        return view;
    }
    //fixme onDestroyView

    public void onClickButtonEmail() {
    }

    public List<String> getActiveUsers() {
        final List<String> userNames = new ArrayList();
        for (User user : userDao.getUsers()) {
            long timestamp = user.getData();
            if (timestamp <= timeLastActivityUser) {
                String name = user.getName();
                String lastName = user.getLastName();
                String email = user.getEmail();
                emails.add(email);
                if (!email.isEmpty() && !email.equalsIgnoreCase("null")) {
                    userNames.add(name + " " + lastName + " : " + email);
                }

            }
        }
        return userNames;

    }

}
