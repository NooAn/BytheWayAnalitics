package com.yanevskyy.y.bythewayanalitics

/**
 * Created by Y on 09.02.18.
 */

import com.firebase.mm.myapplication.User

import java.io.Serializable
import java.util.ArrayList

class UserDao(val users: Collection<User> = ArrayList()) : Serializable