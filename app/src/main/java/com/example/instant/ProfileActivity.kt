package com.example.instant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {
    val db = DB()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        txtUsername.text = db.retrieveCurrentUser()
        txtEmail.text = db.retrieveUserEmail()
    }

    fun goHome(view: View) {
        val myIntent= Intent(this, HomeActivity::class.java)
        startActivity(myIntent)
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right); // slide back to the main activity
    }

    fun goSearch(view: View) {
        val myIntent= Intent(this, SearchActivity::class.java)
        startActivity(myIntent)
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right); // slide back to the main activity
    }


    fun goCamera(view: View) {
        val myIntent= Intent(this, CameraActivity::class.java)
        startActivity(myIntent)
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right); // slide back to the main activity
    }

    fun goLikes(view: View) {
        val myIntent= Intent(this, LikesActivity::class.java)
        startActivity(myIntent)
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right); // slide back to the main activity
    }



}
