package com.example.instant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun signup(view: View) {
        val toast2 = Toast.makeText(applicationContext, "working",Toast.LENGTH_SHORT)
        toast2.setGravity(Gravity.LEFT,200,200)
        toast2.show()
        // Access a Cloud Firestore instance from your Activity
        val db = Firebase.firestore
        // Create a new user with a first and last name
        val user = hashMapOf(
            "first" to "Ada",
            "last" to "Lovelace",
            "born" to 1815
        )

// Add a new document with a generated ID
        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                val myToast = Toast.makeText(applicationContext, documentReference.id,Toast.LENGTH_SHORT)
                myToast.setGravity(Gravity.LEFT,200,200)
                myToast.show()

            }
            .addOnFailureListener { e ->
                val toast = Toast.makeText(applicationContext, "fail",Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.LEFT,200,200)
                toast.show()
            }
        // this button will take you to the sign in page
    }

    fun login(view: View) {
        val toast2 = Toast.makeText(applicationContext, "working",Toast.LENGTH_SHORT)
        toast2.setGravity(Gravity.LEFT,200,200)
        toast2.show()
        // Access a Cloud Firestore instance from your Activity
        val db = Firebase.firestore
        // Create a new user with a first and last name
        val user = hashMapOf(
            "first" to "Ada",
            "last" to "Lovelace",
            "born" to 1815
        )

// Add a new document with a generated ID
        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                val myToast = Toast.makeText(applicationContext, documentReference.id,Toast.LENGTH_SHORT)
                myToast.setGravity(Gravity.LEFT,200,200)
                myToast.show()

            }
            .addOnFailureListener { e ->
                val toast = Toast.makeText(applicationContext, "fail",Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.LEFT,200,200)
                toast.show()
            }
        //this button will take you into your profile page.
//        val myIntent= Intent(this, ProfileActivity::class.java)
//        startActivity(myIntent)
//        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // slide out to next activity animation
    }
}
