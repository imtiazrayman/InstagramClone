package com.example.instant

import android.util.Log
import android.view.Gravity
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DB {



    // Access a Cloud Firestore instance from your Activity
    val db = Firebase.firestore
    //store an image with a given uRL
    fun storeImage(type: String, imageURL: String){
        val image = hashMapOf(
            "${type}" to "${imageURL}"
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
    fun storeVideo(videoURL: String)
    {
        db.collection("videos")
            .add(videoURL)
            .addOnSuccessListener { documentReference ->
            }
            .addOnFailureListener { e ->
            }
    }
    fun retrieveCurrentUser(){
        val user = FirebaseAuth.getInstance().currentUser
    }

    fun retrieveAllImages()
    {
        val images = db.collection("images");
    }
    fun retrieveAllVideos(){
        val videos = db.collection("videos");
    }


}