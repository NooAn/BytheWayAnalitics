package com.yanevskyy.y.bythewayanalitics.repository

import android.content.ContentValues.TAG
import android.util.Log
import com.firebase.mm.myapplication.User
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import java.util.HashMap
import kotlin.collections.ArrayList
import kotlin.collections.component1

class UsersRepository : UserRepositoryContract {

    override fun requestAllUsers(listener: OnRequestedUsers) {
        FirebaseFirestore.getInstance().collection("users").get().addOnSuccessListener { task ->
                val users = ArrayList<User>()
                for (document in task) {
                    Log.d(TAG, document.id + " => " + document.data)
                    users.add(document.toObject(User::class.java))
                }
            listener.onSuccessRequested(users)
        }.addOnFailureListener({ error -> listener.onFailureRequested(error) })
    }
}