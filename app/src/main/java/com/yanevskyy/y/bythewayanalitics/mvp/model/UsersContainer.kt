package com.yanevskyy.y.bythewayanalitics.mvp.model

/**
 * Created by Y on 09.02.18.
 */

import com.firebase.mm.myapplication.User

import java.io.Serializable
import java.util.ArrayList

class UsersContainer(var users: Collection<User> = ArrayList()) : Serializable