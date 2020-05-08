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
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        if(auth.currentUser != null){ // this just checks if we are already signed in
            startActivity(HomeActivity.getLaunchIntent(this))
            finish()
        }


            // what this does is it allows the user to input any email and password they want as long as it matches what is in the firebase console
            // as a test i went into the console and created a fake user called
            // imtiaz@test.com and passoword  = password
            // so if u can sign in with anything pass word
            /*
            now an intersting issue is we can probably try to create a new user an password probably in the sign in where the user can either manually enter email and pass
            and also have the option to sign into google. But for now I havent touched any code in sign in but the code works on two levels as far as authentication.
             */
            btnLogin.setOnClickListener {
            val email = usernameEntered.text.toString()
            val password = passwordEntered.text.toString()
            if(email.isBlank() || password.isBlank()){ //this checks and sees if the password is null or blank.
                // this is if the passord is blamk
                Toast.makeText(this, "Email/password cannot be empty" ,Toast.LENGTH_SHORT)
                return@setOnClickListener
            }
            // Fire base authenication check if these fields are empty
            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener{
                task ->
                if(task.isSuccessful){
                    Toast.makeText(this, "Success!" , Toast.LENGTH_SHORT).show()
                    startActivity(HomeActivity.getLaunchIntent(this))
                    finish()
                }
                else{
                    Toast.makeText(this, "Authentication Failed" , Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
    }
    fun signup(view: View) {
        val myIntent= Intent(this, SignInActivity::class.java)
        startActivity(myIntent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    fun login(view: View) {

       /* val toast2 = Toast.makeText(applicationContext, "working",Toast.LENGTH_SHORT)
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
            }
            .addOnFailureListener { e ->
                val toast = Toast.makeText(applicationContext, "fail",Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.LEFT,200,200)
                toast.show()
            }*/
    }

    fun goProfile(view: View) {}
}
