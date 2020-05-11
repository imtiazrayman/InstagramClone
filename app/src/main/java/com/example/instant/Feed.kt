package com.example.instant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext

class Feed : AppCompatActivity() {
    private val scope = CoroutineScope(newSingleThreadContext("name"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)
        scope.launch { retrieveImages() }



    }
    private suspend fun retrieveImages() {
        var db =  DB()

        try {
            val list = db.retrieveAllImages()
            System.out.println("we are heeeerrreee")


            System.out.println("we are heeeerrreee + $list")

        }
        catch (e: Exception) {
            Log.d(TAG, "$e") //Don't ignore errors!
        }
    }
    fun goHome(view: View) {}
    fun goSearch(view: View) {}
    fun goLikes(view: View) {}
}
