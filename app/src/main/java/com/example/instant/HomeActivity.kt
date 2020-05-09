package com.example.instant

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.instant.models.Users
import com.example.instant.models.Posts
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.activity_home.*

const val TAG = "PostsActivity"
const val EXTRA_USERNAME = "EXTRA_USERNAME"

class HomeActivity : AppCompatActivity() {

    private var signedInUsers: Users? = null
    private lateinit var firestoreDb: FirebaseFirestore
    private lateinit var posts: MutableList<Posts>
    private lateinit var adapter: PostsAdapter

    val RC_SIGN_IN: Int = 1
    lateinit var mGoogleSignInClient: GoogleSignInClient
    lateinit var mGoogleSignInOptions: GoogleSignInOptions

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setupUI();
        configureGoogleSignIn()

        // This is code that is a test to see if the images are actually being taken in.
        /*firestoreDb = FirebaseFirestore.getInstance()
        val postReference = firestoreDb.collection("posts") // call the collection called posts
        postReference.addSnapshotListener{ snapshot, exception ->
            if(exception != null || snapshot == null){
                return@addSnapshotListener
            }
            for(document in snapshot.documents){
                Log.i("HELLO", "Dcoument ${document.id}: ${document.data}")
            }
        }*/


        // Create the layout file which represents one post - DONE
        // Create data source - DONE

        /*posts = mutableListOf()
        // Create the adapter
        adapter = PostsAdapter(this, posts)
        // Bind the adapter and layout manager to the RV
        rvPosts.adapter = adapter
        rvPosts.layoutManager = LinearLayoutManager(this)
        firestoreDb = FirebaseFirestore.getInstance()

        firestoreDb.collection("Users")
            .document(FirebaseAuth.getInstance().currentUser?.uid as String)
            .get()
            .addOnSuccessListener { userSnapshot ->
                signedInUsers = userSnapshot.toObject(Users::class.java)
                Log.i(TAG, "signed in user: $signedInUsers")
            }
            .addOnFailureListener { exception ->
                Log.i(TAG, "Failure fetching signed in user", exception)
            }


        var postsReference = firestoreDb
            .collection("posts")
            .limit(20)
            .orderBy("creation_time_ms", Query.Direction.DESCENDING)

        val username = intent.getStringExtra(EXTRA_USERNAME)
        if (username != null) {
            supportActionBar?.title = username
            postsReference = postsReference.whereEqualTo("Users.username", username)
        }

        postsReference.addSnapshotListener { snapshot, exception ->
            if (exception != null || snapshot == null) {
                Log.e(TAG, "Exception when querying posts", exception)
                return@addSnapshotListener
            }
            val postList = snapshot.toObjects(Posts::class.java)
            posts.clear()
            posts.addAll(postList)
            adapter.notifyDataSetChanged()
            for (post in postList) {

            }
        }*/








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
