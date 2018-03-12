package com.yanevskyy.y.bythewayanalitics.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.yanevskyy.y.bythewayanalitics.R
import com.yanevskyy.y.bythewayanalitics.presenter.SplashPresenter
import org.koin.android.ext.android.inject

class SplashActivity : AppCompatActivity() {
    val presenter: SplashPresenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        presenter.installAllUsers(this)
    }
}