package com.example.instant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Feed : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)
    }

    fun goHome(view: View) {}
    fun goSearch(view: View) {}
    fun goLikes(view: View) {}
}
