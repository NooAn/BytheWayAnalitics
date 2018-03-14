package com.yanevskyy.y.bythewayanalitics.di

import com.yanevskyy.y.bythewayanalitics.catching_users.DbManager
import com.yanevskyy.y.bythewayanalitics.model.UserDao
import com.yanevskyy.y.bythewayanalitics.splash.SplashPresenter
import com.yanevskyy.y.bythewayanalitics.presenter.StatisticPresenter
import com.yanevskyy.y.bythewayanalitics.repository.UserRepositoryContract
import com.yanevskyy.y.bythewayanalitics.repository.UsersRepository
import org.koin.dsl.module.applicationContext

val usersModule = applicationContext {
    bean { UserDao() }
    bean { StatisticPresenter(get()) }
    bean { SplashPresenter(get(), get()) }
    bean { UsersRepository() as UserRepositoryContract }
    bean { DbManager(get()) }
}

val byTheWayStatisticModules = listOf(usersModule)
