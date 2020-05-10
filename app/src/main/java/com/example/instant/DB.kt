package com.example.instant

import android.content.ContentValues.TAG
import android.util.Log
import com.example.instant.models.Posts
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DB {



    // Access a Cloud Firestore instance from your Activity
    val db = Firebase.firestore
    //store an image with a given uRL and type of encoding (bitmap or imageurl) and the user who posted it
    //params: type as a string and imageURL as a string
    //stores in firebase db as {images: {imageID : {type, url, user}}}
    fun storeImage(type: String, imageURL: String , time: String){
        val user  = retrieveCurrentUser()
        val capturetime = time
        val image = hashMapOf(
            "type" to "${type}",
            "url" to "${imageURL}",
            "user" to "${user}",
            "time" to "${capturetime}" // this is added so we can arrange the posts based upon the time sent.
        )
        Posts(imageURL, user.toString(), capturetime)

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
    //returns a collection reference to the images
    //still need to work on this
    fun retrieveAllImages(): Task<QuerySnapshot> {
        var images = db.collection("images")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
            }
        return images
    }
    //returns a collection reference to the images
    //still need to work on this
    fun retrieveAllVideos(): Task<QuerySnapshot> {
        var videos = db.collection("videos")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
            }
        return videos
    }

    // this is a small retriever to display the email for the user in the profile activity.
    fun retrieveUserEmail(): String? {
        return FirebaseAuth.getInstance().currentUser?.email
    }




}