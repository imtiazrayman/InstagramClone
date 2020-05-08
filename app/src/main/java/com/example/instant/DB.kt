package com.example.instant

import android.view.Gravity
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DB {



    // Access a Cloud Firestore instance from your Activity
    val db = Firebase.firestore
    //store an image with a given uRL
    fun storeImage(imageURL: String){
        // Add a new document with a generated ID
        db.collection("images")
            .add(imageURL)
            .addOnSuccessListener { documentReference ->
            }
            .addOnFailureListener { e ->
            }
    }
    fun storeVideo(videoURL: String)
    {
        db.collection("videos")
            .add(videoURL)
            .addOnSuccessListener { documentReference ->
            }
            .addOnFailureListener { e ->
            }
    }

    fun retrieveAllImages()
    {
        val images = db.collection("images");
    }
    fun retrieveAllVideos(){
        val videos = db.collection("videos");
    }


}