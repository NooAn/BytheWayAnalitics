package com.yanevskyy.y.bythewayanalitics.di

import com.yanevskyy.y.bythewayanalitics.catchingusers.DbManager
import com.yanevskyy.y.bythewayanalitics.model.UsersContainer
import com.yanevskyy.y.bythewayanalitics.repository.UserRepositoryContract
import com.yanevskyy.y.bythewayanalitics.repository.UsersRepository
import com.yanevskyy.y.bythewayanalitics.splash.SplashPresenter
import com.yanevskyy.y.bythewayanalitics.statistic.presenter.*
import com.yanevskyy.y.bythewayanalitics.statistic.presentersLol.*
import org.koin.dsl.module.applicationContext

val usersModule = applicationContext {
    bean { UsersContainer() }
    bean { UsersRepository() as UserRepositoryContract }
    bean { DbManager(get()) }
}

val presenterModule = applicationContext {
    bean { SplashPresenter(get(), get(), get()) }
    bean { StatisticActivityPresenter(get()) }
    bean { BudgetPresenter(get()) as SomethingPresenterBudget }
    bean { SearchScreenPresenter(get()) as SomethingPresenterSearchScreen }
    bean { OnlyPhoneNumberPresenter(get()) as SomethingPresenterOnlyPhoneNumber }
    bean { LastActivityUsersPresenter(get()) as SomethingPresenterLastActivityUsers }
    bean { UsersStatisticPresenter(get()) as UsersStatisticPresenterContract }
    bean { TopCitiesPresenter(get()) as TopCitiesPresenterContract }
    bean { SocialNetworksPresenter(get()) as SomethingPresenterSocialNetworks }
    bean { StatisticByParamsPresenter(get()) as StatisticByParamsPresenterContract }
    bean { AddInformationPresenter(get()) as SomethingPresenterAddInformation }
    bean { FlyHoursPresenter(get()) as SomethingPresenterFlyHours }
}

val byTheWayStatisticModules = listOf(usersModule, presenterModule)
