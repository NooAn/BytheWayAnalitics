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
import com.yanevskyy.y.bythewayanalitics.presenter.StatisticPresenter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import org.koin.android.ext.android.inject
import java.security.InvalidKeyException


const val LAST_ACTIVITY = "LAST_ACTIVITY"

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    val presenter: StatisticPresenter by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
        supportFragmentManager.beginTransaction().replace(R.id.container, SearchScreen(), LAST_ACTIVITY).addToBackStack(LAST_ACTIVITY).commit()
    }

    // метод использовать. реализацию изменить! убрать хранение с апп.
    fun getUsers() = presenter

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
            R.id.statistic_by_params -> StatisticByParams()
            R.id.social_networks -> SocialNetworks()
            R.id.budget_statistic -> Budget()
            R.id.users_statistic -> UsersStatistic()
            R.id.only_phone_number -> OnlyPhoneNumber()
            R.id.contains_add_information -> AddInformation()
            R.id.fly_hours -> FlyHours()
            R.id.search_by_name -> SearchScreen()
            else -> throw InvalidKeyException()
        }
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment, LAST_ACTIVITY)
                .addToBackStack(LAST_ACTIVITY).commit()
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
