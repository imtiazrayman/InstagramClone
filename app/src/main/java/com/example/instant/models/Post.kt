package com.example.instant.models

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.firestore.PropertyName
import com.google.firebase.ktx.Firebase

private lateinit var database: DatabaseReference



//var imageurl: String = "/Users/imtiazrayman/Downloads/InstagramClone/app/src/main/res/drawable/sky.jpeg" , var username: String = "" , var time : String = ""
@IgnoreExtraProperties
data class Post(
    var description: String = "",
    @get:PropertyName("url") @set:PropertyName("url") var imageUrl: String = "",
    @get:PropertyName("time") @set:PropertyName("time") var creationTimeMs: Long = 0
) {

    private lateinit var database: DatabaseReference

    private fun writeNewUser(userId: String, name: String, email: String) {
        val user = Users(name, email)
       // database.child("Users").child(userId).setValue(user)

       // database.child("users").child(userId).child("username")


    }

}
