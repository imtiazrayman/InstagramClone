package com.example.instant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class CameraActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
    }


    fun goHome(view: View) {
        val myIntent= Intent(this, HomeActivity::class.java)
        startActivity(myIntent)
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right); // slide back to the main activity
    }

    fun goSearch(view: View) {

    }

    fun goLikes(view: View) {
        val myIntent= Intent(this, CameraActivity::class.java)
        startActivity(myIntent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // slide out to next activity animation
    }

    fun goProfile(view: View) {
        val myIntent= Intent(this, CameraActivity::class.java)
        startActivity(myIntent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // slide out to next activity animation
    }

    fun goCamera(view: View) {}
}
