package com.yanevskyy.y.bythewayanalitics.di

import com.google.gson.GsonBuilder
import com.yanevskyy.y.bythewayanalitics.WebService
import com.yanevskyy.y.bythewayanalitics.catchingusers.DbManager
import com.yanevskyy.y.bythewayanalitics.mvp.model.UsersContainer
import com.yanevskyy.y.bythewayanalitics.repository.UserRepositoryContract
import com.yanevskyy.y.bythewayanalitics.repository.UsersRepository
import com.yanevskyy.y.bythewayanalitics.splash.SplashPresenter
import com.yanevskyy.y.bythewayanalitics.mvp.presenter.*
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.applicationContext
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val servicesModule = applicationContext {
    bean { UsersContainer() }
    bean { UsersRepository() as UserRepositoryContract }
    bean { DbManager(get()) }
}
val remoteDatasourceModule = applicationContext {
    // provided web components
    bean { createOkHttpClient() }
    bean { createWebService<WebService>(get(), SERVER_URL) }
}
val SERVER_URL: String = "https://google.com"
val presenterModule = applicationContext {
    bean { SplashPresenter(get(), get(), get()) }
    bean { BudgetPresenter(get()) }
    bean { CountTokensPresenter(get(), get()) }
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

fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
    return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .readTimeout(5, TimeUnit.SECONDS)
            .connectTimeout(5, TimeUnit.SECONDS)
            .build()
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String): T {
    val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .client(okHttpClient)
            .build()
    return retrofit.create(T::class.java)
}

val byTheWayStatisticModules = listOf(servicesModule, presenterModule, remoteDatasourceModule)

class FireBaseNotification(var title: String = "",
                           var body: String = "",
                           var cmd: String = "",
                           var value: String? = "")