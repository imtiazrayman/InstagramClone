package com.example.instant.models

import android.net.Uri
import com.google.firebase.database.DatabaseReference
import com.google.firebase.ktx.Firebase


private lateinit var database: DatabaseReference




data class Posts(
    var imageurl: String = "/Users/imtiazrayman/Downloads/InstagramClone/app/src/main/res/drawable/sky.jpeg" , var username: String = "" , var time : String = ""
)