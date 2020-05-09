package com.example.instant

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

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
    //returns a collection reference to the images
    //still need to work on this
    fun retrieveAllImages(): CollectionReference {
        val images = db.collection("images");
        return images
    }
    //returns a collection reference to the images
    //still need to work on this
    fun retrieveAllVideos(): CollectionReference {
        val videos = db.collection("videos");
        return videos
    }


}