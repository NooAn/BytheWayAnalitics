package com.yanevskyy.y.bythewayanalitics.activity


import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.yanevskyy.y.bythewayanalitics.R
import com.yanevskyy.y.bythewayanalitics.fragment.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import java.security.InvalidKeyException


const val LAST_ACTIVITY = "LAST_ACTIVITY"
const val USERS_DAO = "USERS_DAO"

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
        supportFragmentManager.beginTransaction().replace(R.id.container, ParseEmails(), LAST_ACTIVITY).addToBackStack(LAST_ACTIVITY).commit()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val fragment: Fragment = when (item.itemId) {
            R.id.last_activity_date -> LastActivityUsers()
            R.id.top_cities -> TopCities()
            R.id.social_networks -> SocialNetworks()
            R.id.budget_statistic -> Budget()
            R.id.users_statistic -> UsersStatistic()
            R.id.only_phone_number -> OnlyPhoneNumber()
            R.id.contains_add_information -> AddInformation()
            R.id.fly_hours -> FlyHours()
            else -> throw InvalidKeyException()
        }
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment, LAST_ACTIVITY)
                .addToBackStack(LAST_ACTIVITY).commit()
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
