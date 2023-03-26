package com.example.learnit

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

lateinit var auth: FirebaseAuth
class LoginActivity : AppCompatActivity() {

    lateinit var gsc: GoogleSignInClient
    var customToken: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_avtivity)

        var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build()
        gsc = GoogleSignIn.getClient(this, gso)
        auth = Firebase.auth

        val loginButton = findViewById<Button>(R.id.login_button)

        loginButton.setOnClickListener {
            signIn()
        }
    }

    fun signIn(){
        startActivityForResult(gsc.signInIntent, 1000)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if( requestCode == 1000 ){
            var task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try{
                task.getResult(ApiException::class.java)
                val currentUser = auth.currentUser

                customToken = GoogleSignIn.getLastSignedInAccount(baseContext)!!.idToken
                val firebaseCredential = GoogleAuthProvider.getCredential(customToken, null)
                if( customToken != null ) {
                    auth.signInWithCredential(firebaseCredential)
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                ///Log.d(TAG, "signInWithCustomToken:success")
                                Toast.makeText(baseContext, "Successful authentication", Toast.LENGTH_SHORT).show()
                                val user = auth.currentUser
                                navigateToMain()
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithCustomToken:failure", task.exception)
                                Toast.makeText(baseContext, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show()
                            }
                        }
                }

            } catch(e: ApiException) {
                println(e)
                Toast.makeText(applicationContext, "Login error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun navigateToMain(){
        finish()
        var intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}

