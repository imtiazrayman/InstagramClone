package com.example.instant

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instant.models.Posts
import com.example.instant.models.Users
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_camera.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.coroutines.*


const val TAG = "PostsActivity"
const val EXTRA_USERNAME = "EXTRA_USERNAME"

class HomeActivity : AppCompatActivity() {

    private val scope = CoroutineScope(newSingleThreadContext("name"))
    var arrayList = ArrayList<Any>()

    var myFile:Uri?=null

    private var signedInUsers: Users? = null
    private lateinit var firestoreDb: FirebaseFirestore
    private lateinit var posts:  ArrayList<Posts>
    private lateinit var adapter: PostAdapter

    val RC_SIGN_IN: Int = 1
    lateinit var mGoogleSignInClient: GoogleSignInClient
    lateinit var mGoogleSignInOptions: GoogleSignInOptions
    val db = DB()
    var imagepath = "Didnt run yet"
    private lateinit var postKey: String
    private lateinit var postReference: DatabaseReference
    private lateinit var commentsReference: DatabaseReference
    private lateinit var database: DatabaseReference
    var arrayList2 = ArrayList<Any>()
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setupUI()
        configureGoogleSignIn()


        scope.launch(Dispatchers.IO) {
            arrayList = retrieveImages() as ArrayList<Any>
            withContext(Dispatchers.Main) {updateGui(arrayList)
            }
        }

        /*val rvposts = findViewById<View>(R.id.rvPosts) as RecyclerView
        // Initialize contacts
        val arr = arrayList as ArrayList<Posts>
        // Create adapter passing in the sample user data
        val adapter = PostAdapter(this, arrayList)
        // Attach the adapter to the recyclerview to populate items
        rvposts.adapter = adapter
        // Set layout manager to position the items
        rvposts.layoutManager = LinearLayoutManager(this)*/


    }











    private fun updateGui(arrayList: ArrayList<Any>) {
       println(this.arrayList[0])
        // textView5.text = arrayList[0].toString()

        var hash : Map<String, String> = this.arrayList[0] as Map<String, String>
        println(hash)

        println(hash["url"])
        imagepath = hash["url"].toString()
        //Log.d("checkurl" , "$imagepath")

        ///imageView3.setImageURI(Uri.parse(hash["url"].toString()))
         textView5.text = hash["url"].toString()
    }








    override fun onStart() {
        super.onStart()
        //imageView3.setImageURI(Uri.fromFile("com.google.android.apps.photos.contentprovider/-1/1/content%3A%2F%2Fmedia%2Fexternal%2Fimages%2Fmedia%2F44/ORIGINAL/NONE/image%2Fjpeg/1801464070"))
    }




    private suspend fun retrieveImages(): Any? {
        var db =  DB()
        var list: Any? = null;
        try {
            list = db.retrieveAllImages()
            System.out.println("we are heeeerrreee")


            System.out.println("we are heeeerrreee + $list")
           // textView5.text = "$list"

        }
        catch (e: Exception) {
            Log.d(TAG, "$e") //Don't ignore errors!
        }
        return list
    }


    //sets the google sign in options to default with our web client id (api key)
    private fun configureGoogleSignIn() {
        mGoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, mGoogleSignInOptions)
    }
    //sets an on click listener to the google sign out button
    private fun setupUI() {
        sign_out_button.setOnClickListener {
            signOut()
        }
    }
    //sign the user out of the firebase account and google account. Also starts the Sign in Activity
    private fun signOut() {
        FirebaseAuth.getInstance().signOut();
        googleSignOut()
        startActivity(SignInActivity.getLaunchIntent(this))


    }
    //logs a user out of google
    private fun googleSignOut() {
        mGoogleSignInClient.signOut()
            .addOnCompleteListener(this, OnCompleteListener<Void?> {
                // ...
            })
    }
    companion object {
        var path = ""
        fun getLaunchIntent(from: Context) = Intent(from, HomeActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }


//launches the search activity with an animation
    fun goSearch(view: View) {
        val myIntent= Intent(this, SearchActivity::class.java)
        startActivity(myIntent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // slide out to next activity animation
    }

//launches the camera activity with an animation
    fun goCamera(view: View) {
        val myIntent= Intent(this, CameraActivity::class.java)
        startActivity(myIntent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // slide out to next activity animation
    }
//launches the likes activity with an animation
    fun goLikes(view: View) {
        val myIntent= Intent(this, LikesActivity::class.java)
        startActivity(myIntent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // slide out to next activity animation
    }
//launches the profile activity with an animation
    fun goProfile(view: View) {
        val myIntent= Intent(this, ProfileActivity::class.java)
        startActivity(myIntent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // slide out to next activity animation
    }




}


