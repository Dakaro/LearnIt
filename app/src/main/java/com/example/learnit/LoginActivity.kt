package com.example.learnit

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_avtivity)


        val loginButton = findViewById<Button>(R.id.login_button)

        loginButton.setOnClickListener {
            navigateToMain()
        }
    }

    fun navigateToMain(){
        finish()
        var intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}

