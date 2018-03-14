package com.yanevskyy.y.bythewayanalitics.splash

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.yanevskyy.y.bythewayanalitics.R
import com.yanevskyy.y.bythewayanalitics.activity.MainActivity
import org.koin.android.ext.android.inject

class SplashActivity : AppCompatActivity(), SplashActivityContract {

    val presenter: SplashPresenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun onResume() {
        super.onResume()
        presenter.attachView(this)
        presenter.installAllUsers()
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView()
    }

    override fun onInstallUsers() {
        startActivity(Intent(this, MainActivity::class.java))
    }
}