package com.yanevskyy.y.bythewayanalitics


import android.content.ContentValues.TAG
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.MenuItem
import android.view.View
import com.firebase.mm.myapplication.User
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.yanevskyy.y.bythewayanalitics.fragment.FragmentLastActivityUsers
import java.util.*

class MainActivity() : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var lastActivityUsers: FragmentLastActivityUsers
    lateinit var userDao: UserDao
    private lateinit var users: MutableCollection<User>
    private var db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)


        users = HashSet()
        db.collection("users").get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                for (document in task.result) {
                    Log.d(TAG, document.id + " => " + document.data)
                    document.reference.get().addOnSuccessListener { documentSnapshot -> users.add(fillUserDao(documentSnapshot)) }

                }
            } else {
                Log.w(TAG, "Error getting documents.", task.exception)
            }
            userDao = UserDao(users)
        }


        lastActivityUsers = FragmentLastActivityUsers()
    }

    override fun onBackPressed() {
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val transaction = supportFragmentManager.beginTransaction()

        val id = item.itemId


        when (id) {
            R.id.last_activity_date -> {
                val bundle = Bundle()
                bundle.putSerializable(USERS_DAO, userDao)
                val lastActivityUsers = FragmentLastActivityUsers()
                lastActivityUsers.arguments = bundle
                transaction.replace(R.id.container, lastActivityUsers, LAST_ACTIVITY).addToBackStack(LAST_ACTIVITY).commit()
            }
            R.id.statistic_on_param -> {
            }
            R.id.top_cities -> {
            }
            R.id.count_users -> {
            }
            R.id.social_network -> {
            }
            R.id.with_out_travel -> {
                //todo
            }
        }

        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    fun fillUserDao(documentSnapshot: DocumentSnapshot): User {
        val user = User()
        if (documentSnapshot.exists()) {
            for ((field) in documentSnapshot.data) {
                when (field) {
                //Fixme   user = document.toObject(User::class.java)

                    "addInformation" -> user.addInformation = documentSnapshot.getString(field)
                    "age" -> user.age = documentSnapshot.getLong(field)!!.toInt()
                    "budget" -> user.budget = documentSnapshot.getLong(field)
                    "budgetPosition" -> user.budgetPosition = documentSnapshot.getLong(field)!!.toInt()
                    "cities" -> user.cities = documentSnapshot.get(field) as HashMap<String, String>
                    "city" -> user.city = documentSnapshot.getString("city")
                    "cityFromLatLng" -> user.cityFromLatLng = documentSnapshot.getGeoPoint("cityFromLatLng")
                    "cityToLatLng" -> user.cityToLatLng = documentSnapshot.getGeoPoint("cityToLatLng")
                    "countTrip" -> user.countTrip = documentSnapshot.getLong("countTrip")!!.toInt()
                    "countries" -> user.countries = documentSnapshot.getLong("countries")
                    "timestamp" -> user.data = documentSnapshot.getDate("timestamp").time
                    "dates" -> user.dates = documentSnapshot.get("dates") as HashMap<String, Long>
                    "email" -> user.email = documentSnapshot.getString("email")
                    "flightHours" -> user.flightHours = documentSnapshot.getLong("flightHours")
                    "id" -> user.id = documentSnapshot.getString("id")
                    "kilometers" -> user.kilometers = documentSnapshot.getLong("kilometers")
                    "lastName" -> user.lastName = documentSnapshot.getString("lastName")
                    "method" -> user.method = documentSnapshot.get("method") as HashMap<String, Boolean>
                    "name" -> user.name = documentSnapshot.getString("name")
                    "percentsSimilarTravel" -> user.percentsSimilarTravel = documentSnapshot.getLong("percentsSimilarTravel")!!.toInt()
                    "phone" -> user.phone = documentSnapshot.getString("phone")
                    "route" -> user.route = documentSnapshot.getString("route")
                    "sex" -> user.sex = documentSnapshot.getLong("sex")!!.toInt()
                    "socialNetwork" -> user.socialNetwork = documentSnapshot.get("socialNetwork") as HashMap<String, String>
                    "urlPhoto" -> user.urlPhoto = documentSnapshot.getString("urlPhoto")
                    else -> {
                    }
                }
            }
        }
        return user
    }

    companion object {

        val LAST_ACTIVITY = "LAST_ACTIVITY"
        val USERS_DAO = "USERS_DAO"
    }
}
