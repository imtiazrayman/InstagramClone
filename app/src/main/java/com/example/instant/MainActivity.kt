package com.example.instant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_main)
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser

    }
    fun signup(view: View) {
        val myIntent= Intent(this, SignInActivity::class.java)
        startActivity(myIntent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // slide out to next activity animation


           // this button will take you to the sign in page
    }

    fun login(view: View) {
        val toast2 = Toast.makeText(applicationContext, "working",Toast.LENGTH_SHORT)
        toast2.setGravity(Gravity.LEFT,200,200)
        toast2.show()
        // Access a Cloud Firestore instance from your Activity
        val db = Firebase.firestore

        // Create a new user with a first and last name //Ada
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

                // this is test code so i can see how the ui looks like since i cant sign in atm
               /* val myIntent= Intent(this, HomeActivity::class.java)
                startActivity(myIntent)
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // slide out to next activity animation*/

                /*val myIntent= Intent(this, ProfileActivity::class.java)
                startActivity(myIntent)
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // slide out to next activity animation*/



            }
            .addOnFailureListener { e ->
                val toast = Toast.makeText(applicationContext, "fail",Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.LEFT,200,200)
                toast.show()
            }
        //this button will take you into your profile page.
//        val myIntent= Intent(this, ProfileActivity::class.java)
//        startActivity(myIntent)
//         overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // slide out to next activity animation
    }
}
