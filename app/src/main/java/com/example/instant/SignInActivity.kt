package com.example.instant

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {
    val RC_SIGN_IN: Int = 1
    lateinit var mGoogleSignInClient: GoogleSignInClient
    lateinit var mGoogleSignInOptions: GoogleSignInOptions
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        //gets an instance of firebase authentication object
        firebaseAuth = FirebaseAuth.getInstance()
        setupUI()
        configureGoogleSignIn()

    }
    //authenticates a google account with firebase
    //params : account with type GoogleSignInAccount

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        //grabs a google credential
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        //converts google credential into a firebase credential
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                //launches home activity
                startActivity(HomeActivity.getLaunchIntent(this))
            } else {
                //failure toasts and tests
                Toast.makeText(this, "Google sign in failed in firebase authentication:(", Toast.LENGTH_LONG).show()
                Toast.makeText(this, "${credential}", Toast.LENGTH_LONG).show()
                Toast.makeText(this, "${acct}", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onStart() {
        super.onStart()


        val account = GoogleSignIn.getLastSignedInAccount(this)

        val user = FirebaseAuth.getInstance().currentUser
        //checks for existing firebase account. null if not logged in
        if (user != null) {
            startActivity(HomeActivity.getLaunchIntent(this))
            finish()
        }
        // Check for existing Google Sign In account, if the user is already signed in
// the GoogleSignInAccount will be non-null.
        else if (account != null) {
            firebaseAuthWithGoogle(account);
            finish()
        }
    }
    //configures google sign in options with default and our web client id (api key)
    private fun configureGoogleSignIn() {
        mGoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, mGoogleSignInOptions)
    }
    //sets an onclick listener for our google sign in button
    private fun setupUI() {
        google_button.setOnClickListener {
            signIn()
        }
    }
    //starts the sign in activity
    private fun signIn() {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }
    //google sign in and authentication occur here
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {

            var account: GoogleSignInAccount? = null
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                 account = task.getResult(ApiException::class.java)
                if (account != null) {
                    firebaseAuthWithGoogle(account)
                }
            } catch (e: ApiException) {
                Toast.makeText(this, "Google sign in failed in google section :(", Toast.LENGTH_LONG).show()
                Toast.makeText(this, "${account}:(", Toast.LENGTH_LONG).show()
                Toast.makeText(this, "${task}:(", Toast.LENGTH_LONG).show()
            }
        }
    }
    companion object {
        fun getLaunchIntent(from: Context) = Intent(from, SignInActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }
}
