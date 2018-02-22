package com.yanevskyy.y.bythewayanalitics.dagger

import com.yanevskyy.y.bythewayanalitics.MainActivity
import com.yanevskyy.y.bythewayanalitics.SplashActivity
import com.yanevskyy.y.bythewayanalitics.fragment.BudgetFragment
import com.yanevskyy.y.bythewayanalitics.fragment.FragmentLastActivityUsers
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [(AppModule::class)])

interface AppComponent {
    fun inject(fragment: BudgetFragment)
    fun inject(fragment: FragmentLastActivityUsers)
    fun inject(activity: SplashActivity)
}