package com.yanevskyy.y.bythewayanalitics

import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.QueryMap

interface WebService {

    //@POST("https://bytheway-f98ba.firebaseapp.com/sendnotifications")
    @POST("https://bytheway-c7b6a.firebaseapp.com/sendnotifications") // for release
    fun sendNotifications(@Body queryMap: Map<String, String>): Completable
}