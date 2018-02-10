package com.yanevskyy.y.bythewayanalitics;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.firebase.mm.myapplication.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.yanevskyy.y.bythewayanalitics.fragment.FragmentLastActivityUsers;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String LAST_ACTIVITY = "LAST_ACTIVITY";
    public static final String USERS_DAO = "USERS_DAO";
    FragmentLastActivityUsers lastActivityUsers;
    UserDao userDao;
    Collection<User> users;
    FirebaseFirestore db = FirebaseFirestore.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        users = new HashSet<>();
        db.collection("users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (final DocumentSnapshot document : task.getResult()) {
                        Log.d(TAG, document.getId() + " => " + document.getData());
                        document.getReference().get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                users.add(fillUserDao(documentSnapshot));
                            }
                        });

                    }
                } else {
                    Log.w(TAG, "Error getting documents.", task.getException());
                }
                userDao = new UserDao(users);
            }

        });


        lastActivityUsers = new FragmentLastActivityUsers();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        int id = item.getItemId();

        if (id == R.id.last_activity_date) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(USERS_DAO, userDao);
            FragmentLastActivityUsers lastActivityUsers = new FragmentLastActivityUsers();
            lastActivityUsers.setArguments(bundle);
            transaction.replace(R.id.container, lastActivityUsers, LAST_ACTIVITY).addToBackStack(LAST_ACTIVITY).commit();
        } else if (id == R.id.statistic_on_param) {

        } else if (id == R.id.top_cities) {

        } else if (id == R.id.count_users) {

        } else if (id == R.id.social_network) {

        } else if (id == R.id.with_out_travel) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public User fillUserDao(DocumentSnapshot documentSnapshot) {
        User user = new User();
        if (documentSnapshot.exists()) {
            for (Map.Entry<String, Object> map : documentSnapshot.getData().entrySet()) {
                String field = map.getKey();
                switch (field) {
                    case "addInformation": user.setAddInformation(documentSnapshot.getString(field)); break;
                    case "age": user.setAge(documentSnapshot.getLong(field).intValue()); break;
                    case "budget": user.setBudget(documentSnapshot.getLong(field)); break;
                    case "budgetPosition": user.setBudgetPosition(documentSnapshot.getLong(field).intValue());break;
                    case "cities": user.setCities((HashMap<String, String>) documentSnapshot.get(field)); break;
                    case "city": user.setCity(documentSnapshot.getString("city")); break;
                    case "cityFromLatLng": user.setCityFromLatLng(documentSnapshot.getGeoPoint("cityFromLatLng")); break;
                    case "cityToLatLng": user.setCityToLatLng(documentSnapshot.getGeoPoint("cityToLatLng")); break;
                    case "countTrip": user.setCountTrip(documentSnapshot.getLong("countTrip").intValue()); break;
                    case "countries": user.setCountries(documentSnapshot.getLong("countries")); break;
                    case "timestamp": user.setData(documentSnapshot.getDate("timestamp").getTime()); break;
                    case "dates": user.setDates((HashMap<String, Long>) documentSnapshot.get("dates")); break;
                    case "email": user.setEmail(documentSnapshot.getString("email")); break;
                    case "flightHours": user.setFlightHours(documentSnapshot.getLong("flightHours")); break;
                    case "id": user.setId(documentSnapshot.getString("id")); break;
                    case "kilometers": user.setKilometers(documentSnapshot.getLong("kilometers")); break;
                    case "lastName": user.setLastName(documentSnapshot.getString("lastName")); break;
                    case "method": user.setMethod((HashMap<String, Boolean>) documentSnapshot.get("method")); break;
                    case "name": user.setName(documentSnapshot.getString("name")); break;
                    case "percentsSimilarTravel": user.setPercentsSimilarTravel(documentSnapshot.getLong("percentsSimilarTravel").intValue()); break;
                    case "phone": user.setPhone(documentSnapshot.getString("phone")); break;
                    case "route": user.setRoute(documentSnapshot.getString("route")); break;
                    case "sex": user.setSex(documentSnapshot.getLong("sex").intValue()); break;
                    case "socialNetwork": user.setSocialNetwork((HashMap<String, String>) documentSnapshot.get("socialNetwork")); break;
                    case "urlPhoto": user.setUrlPhoto(documentSnapshot.getString("urlPhoto")); break;
                    default:break;

                }
            }
        }
        return user;
    }
}
