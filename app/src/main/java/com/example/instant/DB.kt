package com.example.instant

import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await


class DB {



    // Access a Cloud Firestore instance from your Activity
    val db = Firebase.firestore
    //store an image with a given uRL and type of encoding (bitmap or imageurl) and the user who posted it
    //params: type as a string and imageURL as a string
    //stores in firebase db as {images: {imageID : {type, url, user}}}

    fun storeImage(type: String, imageURL: String){
        val user  = retrieveCurrentUser()
        val image = hashMapOf(
            "type" to "${type}",
            "url" to "${imageURL}",
            "user" to "${user}"
        )

        // Add a new document with a generated ID
        Log.e("testing", "am I in the db");
        db.collection("images")


                    .add(image)
            .addOnSuccessListener { documentReference ->
                Log.e("testing", "am I in the reference ${documentReference.id}");
            }
            .addOnFailureListener { e ->
                Log.e("testing", "am I in the failure reference");
            }
    }
    //store a video in firebase dB
    //params: type as a string and the video url as a string
    //stores in firebase db as {videos: {videoID : {type, url, user}}}

    fun storeVideo(type: String, videoURL: String)
    {
        val user  = retrieveCurrentUser()
        val video = hashMapOf(
            "type" to "${type}",
            "url" to "${videoURL}",
            "user" to "${user}"
        )
        db.collection("videos")
            .add(video)
            .addOnSuccessListener { documentReference ->
            }
            .addOnFailureListener { e ->
            }
    }
    //retrieves the current user from firebase
    fun retrieveCurrentUser(): String? {
        return FirebaseAuth.getInstance().currentUser?.displayName
    }
    suspend fun retrieveAllImages(): ArrayList<Any> {
        val snapshot = db.collection("images").get().await()
        var arrayList = ArrayList<Any>()
        for(document in snapshot){
            val image = hashMapOf(
                "time" to "${document.data["time"]}",
                "type" to "${document.data["type"]}",
                "url" to "${document.data["url"]}",
                "user" to "${document.data["user"]}"
            )
            arrayList.add(image)
        }
        return arrayList
    }
    //returns a collection reference to the images
    //still need to work on this

    //returns a collection reference to the images
    //still need to work on this
    suspend fun retrieveAllVideos(): ArrayList<Any> {
        val snapshot = db.collection("videos").get().await()
        var arrayList = ArrayList<Any>()
        for(document in snapshot){
            val video = hashMapOf(
                "time" to "${document.data["time"]}",
                "type" to "${document.data["type"]}",
                "url" to "${document.data["url"]}",
                "user" to "${document.data["user"]}"
            )
            arrayList.add(video)
        }
        return arrayList
    }


    // this is a small retriever to display the email for the user in the profile activity.
    fun retrieveUserEmail(): String? {
        return FirebaseAuth.getInstance().currentUser?.email
    }

}