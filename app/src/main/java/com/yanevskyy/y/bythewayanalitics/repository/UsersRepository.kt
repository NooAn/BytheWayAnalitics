package com.yanevskyy.y.bythewayanalitics.repository

import android.content.ContentValues
import android.util.Log
import com.firebase.mm.myapplication.User
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import java.util.HashMap
import kotlin.collections.ArrayList
import kotlin.collections.component1

class UsersRepository : UserRepositoryContract{

    override fun requestAllUsers(listener: OnRequestedUsers) {
        //FIXME да, это надо вставить Олег, вместо той фигни что ниже :)
        /*
                   store.collection(COLLECTION_USERS).get().addOnCompleteListener({ task ->
                       if (task.isSuccessful) {
                           val result: MutableList<User> = ArrayList()
                           for (document in task.result) {
                               Log.d(TAG, document.id + " => " + document.data)
                               var user = User()
                                   user = document.toObject(User::class.java)
*/
        FirebaseFirestore.getInstance().collection("users").get().addOnSuccessListener { task ->
            val users = ArrayList<User>()
            task.documents.forEach {
                Log.d(ContentValues.TAG, it.id + " => " + it.data)
                users.add(fillUserDao(it))
            }
            listener.onSuccessRequested(users)
        }.addOnFailureListener({ error -> listener.onFailureRequested(error) })
    }

    private fun fillUserDao(documentSnapshot: DocumentSnapshot): User {
        val user = User()
        if (documentSnapshot.exists()) {
            user.id = documentSnapshot.id
            for ((field) in documentSnapshot.data) {
                when (field) {
                //Fixme   user = document.toObject(User::class.java)
                    "addInformation" -> user.addInformation = documentSnapshot.getString(field)
                    "age" -> user.age = documentSnapshot.getLong(field)!!.toInt()
                    "budget" -> user.budget = documentSnapshot.getLong(field)
                    "budgetPosition" -> user.budgetPosition = documentSnapshot.getLong(field)!!.toInt()
                    "cities" -> user.cities = documentSnapshot.get(field) as HashMap<String, String>
                    "city" -> user.city = documentSnapshot.getString("city")
                    "cityFromLatLng" -> user.cityFromLatLng = documentSnapshot.getGeoPoint("cityFromLatLng")
                    "cityToLatLng" -> user.cityToLatLng = documentSnapshot.getGeoPoint("cityToLatLng")
                    "countTrip" -> user.countTrip = documentSnapshot.getLong("countTrip")!!.toInt()
                    "countries" -> user.countries = documentSnapshot.getLong("countries")
                    "timestamp" -> user.data = documentSnapshot.getDate("timestamp").time
                    "dates" -> user.dates = documentSnapshot.get("dates") as HashMap<String, Long>
                    "email" -> user.email = documentSnapshot.getString("email")
                    "flightHours" -> user.flightHours = documentSnapshot.getLong("flightHours")
                    "kilometers" -> user.kilometers = documentSnapshot.getLong("kilometers")
                    "lastName" -> user.lastName = documentSnapshot.getString("lastName")
                    "method" -> user.method = documentSnapshot.get("method") as HashMap<String, Boolean>
                    "name" -> user.name = documentSnapshot.getString("name")
                    "percentsSimilarTravel" -> user.percentsSimilarTravel = documentSnapshot.getLong("percentsSimilarTravel")!!.toInt()
                    "phone" -> user.phone = documentSnapshot.getString("phone")
                    "route" -> user.route = documentSnapshot.getString("route")
                    "sex" -> user.sex = documentSnapshot.getLong("sex")!!.toInt()
                    "socialNetwork" -> user.socialNetwork = documentSnapshot.get("socialNetwork") as HashMap<String, String>
                    "urlPhoto" -> user.urlPhoto = documentSnapshot.getString("urlPhoto")
                }
            }
        }
        return user
    }
}