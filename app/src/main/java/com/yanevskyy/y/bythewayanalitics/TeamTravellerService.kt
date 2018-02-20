package com.yanevskyy.y.bythewayanalitics

import com.firebase.mm.myapplication.TeamUserData
import io.reactivex.Single
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Andrei_Gusenkov on 2/20/2018.
 */
interface TeamTravellers {
    @GET("/user")
    fun getUser(@Query("Task") task: String = "GetUser",
                @Query("id_user") id: Int = 8906): Deferred<TeamUserData>

    @GET("/user")
    fun getUserRx(@Query("Task") task: String = "GetUser",
                  @Query("id_user") id: Int = 8906): Single<TeamUserData>
}