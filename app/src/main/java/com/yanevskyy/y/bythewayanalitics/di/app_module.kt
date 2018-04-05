package com.yanevskyy.y.bythewayanalitics.di

import com.yanevskyy.y.bythewayanalitics.catchingusers.DbManager
import com.yanevskyy.y.bythewayanalitics.model.UsersContainer
import com.yanevskyy.y.bythewayanalitics.repository.UserRepositoryContract
import com.yanevskyy.y.bythewayanalitics.repository.UsersRepository
import com.yanevskyy.y.bythewayanalitics.splash.SplashPresenter
import com.yanevskyy.y.bythewayanalitics.statistic.presenter.*
import org.koin.dsl.module.applicationContext

val servicesModule = applicationContext {
    bean { UsersContainer() }
    bean { UsersRepository() as UserRepositoryContract }
    bean { DbManager(get()) }
}

val presenterModule = applicationContext {
    bean { SplashPresenter(get(), get(), get()) }
    bean { BudgetPresenter(get()) }
    bean { CountTokensPresenter(get()) }
    bean { SearchScreenPresenter(get()) }
    bean { OnlyPhoneNumberPresenter(get()) }
    bean { LastActivityUsersPresenter(get()) }
    bean { UsersStatisticPresenter(get()) }
    bean { TopCitiesPresenter(get()) }
    bean { SocialNetworksPresenter(get()) }
    bean { StatisticByParamsPresenter(get()) }
    bean { AddInformationPresenter(get()) }
    bean { FlyHoursPresenter(get()) }
}

val byTheWayStatisticModules = listOf(servicesModule, presenterModule)
